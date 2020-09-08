package it.unimib.disco.essere.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.bcel.classfile.JavaClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.process.traversal.util.FastNoSuchElementException;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterDescription;
import com.beust.jcommander.ParametersDelegate;

import it.unimib.disco.essere.main.asengine.CyclicDependencyDetector;
import it.unimib.disco.essere.main.asengine.HubLikeDetector;
import it.unimib.disco.essere.main.asengine.UnstableDependencyDetector;
import it.unimib.disco.essere.main.asengine.cycleutils.CDFilterUtils;
import it.unimib.disco.essere.main.asengine.cycleutils.CyclePrinter;
import it.unimib.disco.essere.main.asengine.cycleutils.PrintShapesDocker;
import it.unimib.disco.essere.main.asengine.cycleutils.PrintToMatrix;
import it.unimib.disco.essere.main.asengine.cycleutils.PrintToTable;
import it.unimib.disco.essere.main.asengine.filters.CDShapeFilter;
import it.unimib.disco.essere.main.asengine.filters.UDRateFilter;
import it.unimib.disco.essere.main.asengine.udutils.UDPrinter;
import it.unimib.disco.essere.main.asengine.udutils.UDUtils;
import it.unimib.disco.essere.main.graphmanager.GraphBuilder;
import it.unimib.disco.essere.main.graphmanager.GraphReader;
import it.unimib.disco.essere.main.graphmanager.GraphUtils;
import it.unimib.disco.essere.main.graphmanager.GraphWriter;
import it.unimib.disco.essere.main.graphmanager.Neo4JGraphWriter;
import it.unimib.disco.essere.main.graphmanager.PropertyEdge;
import it.unimib.disco.essere.main.graphmanager.TypeVertexException;
import it.unimib.disco.essere.main.metricsengine.ClassMetricsCalculator;
import it.unimib.disco.essere.main.metricsengine.MetricsUploader;
import it.unimib.disco.essere.main.metricsengine.PackageMetricsCalculator;
import it.unimib.disco.essere.main.systemreconstructor.SystemBuilder;
import it.unimib.disco.essere.main.systemreconstructor.SystemBuilderByFolderOfJars;
import it.unimib.disco.essere.main.systemreconstructor.SystemBuilderByJar;
import it.unimib.disco.essere.main.systemreconstructor.SystemBuilderByUrl;
import it.unimib.disco.essere.main.terminal.ExistFile;
import it.unimib.disco.essere.main.terminal.ExistJavaVersion;
import it.unimib.disco.essere.main.terminal.FileConverter;
import it.unimib.disco.essere.main.terminal.JavaVersionConverter;
import it.unimib.disco.essere.main.terminal.ParameterGitValueTerminal;
import it.unimib.disco.essere.main.terminal.ParameterInputProjectInputTerminal;
import it.unimib.disco.essere.main.terminal.ParametersComputeMetricsTerminal;
import it.unimib.disco.essere.main.terminal.ParametersDetectionArchitecturalSmell;
import it.unimib.disco.essere.main.terminal.ParametersNeo4jDBTerminal;
import it.unimib.disco.essere.main.terminal.PositiveInteger;
import it.unimib.disco.essere.test.QualitasCorpusTest;

public class TerminalExecutorOld {

	static final Logger logger = LogManager.getLogger(TerminalExecutorOld.class);
	SystemBuilder _sys = null;
	private PackageMetricsCalculator _packageMetricsCalculator = null;
	private ClassMetricsCalculator _classMetricsCalculator = null;
	private UnstableDependencyDetector _unstableDependencyDetector = null;
	private CyclicDependencyDetector _cycleDetector = null;
	private HubLikeDetector _hubLikeDetector = null;

	public UnstableDependencyDetector getUnstableDependencyDetector() {
		return _unstableDependencyDetector;
	}


	@Parameter(names = { "-log", "-verbose", "-v" }, description = "Level of verbosity", validateWith = PositiveInteger.class, descriptionKey="verbose")
	private Integer _verbose = 0;

	@Parameter(names = { "-help", "-h" },help = true, description = "Print this help", descriptionKey="help")
	private boolean _help = false;

	@ParametersDelegate
	private static ParametersComputeMetricsTerminal _parMetric = ParametersComputeMetricsTerminal.getInstance();

	@ParametersDelegate
	private static ParametersDetectionArchitecturalSmell _parAS = new ParametersDetectionArchitecturalSmell();
	//	private final static ParametersAllValue _parAll = new ParametersAllValue();

	@Parameter( names={"-all"} ,description = "Calculates all metrics and all type of architectural smells", descriptionKey="detection")
	public boolean _all = false;

	@ParametersDelegate
	private static ParametersNeo4jDBTerminal _parNeo4j = ParametersNeo4jDBTerminal.getInstance();

	@ParametersDelegate
	private static ParameterInputProjectInputTerminal _parProject = ParameterInputProjectInputTerminal.getInstance();

	@Parameter(names = { "-filter", "-F", "-f" }, hidden = true, description = "Filter the results of the smells detection", descriptionKey="detection")
	private boolean _filter = true;

	//	@Parameter(names = { "-neo4j" }, description = "if set write the neo4j database")
	//	private boolean _writeNeo4j = false;
	//	
	//	@Parameter(names = { "-dbFolder","-d" }, description = "Database folder (default here_path\\Neo4j\\default.graphdb)", converter = FileConverter.class, validateWith = ExistFile.class)
	//	File _dbFolder = Paths.get("Neo4j", "default.graphdb").toAbsolutePath().toFile();
	//	
	@Parameter(names = { "-outputDir", "-out" }, description = "output dir of results", converter = FileConverter.class, validateWith = ExistFile.class, descriptionKey="output")
	private File _outDir = null;

	//FIXME below are all hidden parameters
	@Parameter(names = { "-classpathFolder", "-c" }, hidden = true, description = "Classpath system (folder of the system jar files)", converter = FileConverter.class, validateWith = ExistFile.class)
	File _classPathFolder = null;

	@Parameter(names = { "-libraryFolder", 	"-l" }, hidden = true, description = "Library folder (folder of library jar files)", converter = FileConverter.class, validateWith = ExistFile.class)
	File _libFolder = null;

	@ParametersDelegate
	ParameterGitValueTerminal _parGit = new ParameterGitValueTerminal();

	@Parameter(names = "-cycleTest", hidden = true, description = "test")
	private boolean _cycleTest = false;

	@Parameter(names = { "-javaversion", "-jv" }, hidden = true, description = "Version of JVM, e.g.,1.8,1.7.", converter = JavaVersionConverter.class, validateWith = ExistJavaVersion.class)
	private String _jv = JavaVersionConverter._j8;

	static JCommander _k = null;


	private static final String FILE_HUB_LIKE = "HL.csv";
	private static final String FILE_CYCLE = "CL.csv";
	private static final String FILE_UNSTABLE_DEPENDECY = "UD.csv";
	private static final String FILE_UNSTABLE_DEPENDECY_FILTERED_30 = "UD30.csv";
	private static final String FILE_PACKAGE_METRICS = "PM.csv";
	private static final String FILE_CLASS_METRICS = "CM.csv";


	/**
	 * It required pass the string of the complete path of the project folder
	 * input: -p
	 * "C:\\Users\\ricca\\workspaceThinkerpop\\ToySystem\\target\\classes\\it\\unimib\\disco\\essere\\toysystem"
	 * 
	 * @author RR
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.logging.log4j.core.config.Configurator.setRootLevel(Level.INFO);
		logger.info("***Start of Terminal Executor***");
		TerminalExecutorOld tt = new TerminalExecutorOld();
		_k = new JCommander(tt);
		_k.setCaseSensitiveOptions(false);
		_k.setProgramName("java -jar Arcan.jar");
		//		_k.addCommand("metric",_parMetric);
		//		_k.addCommand("as",_parAS);
		//		_k.addCommand("all",_parAll);
		//		_k.addCommand("neo4j",_parNeo4j);
		_k.parse(args);
		logger.info("*** args:" + args.length + "***");
		try {
			tt.run(args);
		} catch (TypeVertexException e1) {
			//			logger.error(e1.getMessage()+e1.fillInStackTrace(), e1.fillInStackTrace());
			e1.printStackTrace();
		} catch (Exception e) {
			//			logger.error(e.getLocalizedMessage()+e.fillInStackTrace().getStackTrace(), e.fillInStackTrace());
			//			logger.catching(e.fillInStackTrace());
			e.printStackTrace();
		}
		logger.info("***End of Terminal Executor***");
		Runtime.getRuntime().exit(0);
	}

	public void run(String[] args) throws TypeVertexException {

		if (_verbose > 0) {
			org.apache.logging.log4j.core.config.Configurator.setRootLevel(Level.DEBUG);
			// is the case when only verbose is called
			if (args.length == 2) {
				_help = true;
			}
		}
		if (args.length == 0 || _help) {
			//			_k.usage();

			prettyPrintOutput();

		} else {
			Graph graph = null;
			try {
				if (_cycleTest) {
					QualitasCorpusTest.main(null);
				} else if ( 
						(_parAS._cycle 
								|| _parAS._UnstableDependencies 
								|| _parAS._HubLikeDependencies 
								|| _parMetric._PackageMetrics
								|| _parMetric._ClassMetrics
								|| _all)) {

					if (_parProject._projectFolder != null) {
						if (_parNeo4j._writeNeo4j) {
							graph = writeGraph();
						} else {
							graph = writeGraphTinkerpop(_parProject._projectFolder);
						}
					} else if (!_parNeo4j._dbFolder.exists() || _parNeo4j._dbFolder.getTotalSpace() <= 0) {
						throw new IOException("Folder doesn't exist or it's empty.");
					} else {
						graph = readGraph();
					}

					if (_outDir != null) {
						logger.info("***Output folder creating***"+File.separator+_outDir.getName());
						OutputDirUtils.createDirFullPath(_outDir, _parProject._jarMode);
						logger.info("***Output folder created***"+OutputDirUtils.getOutputFolder());
					}
					else{
						logger.info("***Output folder creating***"+File.separator);
						OutputDirUtils.createDirFullPath();
						logger.info("***Output folder created***"+OutputDirUtils.getOutputFolder());
					}

					if (_all || _parAS._cycle) {
						logger.info("***Start Cycle detection***" + graph);
						runCycleDetector(graph);
						logger.info("***End of Cycle detection***" + graph);
						if (_filter) {
							logger.info("***Start Cycle filtering***" + graph);
							runCycleDetectorShapeFilter(graph);
							logger.info("***End of Cycle filtering***" + graph);
						}
					}
					if (_all || _parAS._UnstableDependencies) {
						logger.info("***Start Unstable dependencies detection***" + graph);
						runUnstableDependencies(graph);
						logger.info("***End of Unstable dependencies detection***"+ graph);
						if (_filter) {
							logger.info("***Start Unstable dependencies filtering***" + graph);
							runUnstableDependencyFilter(graph);
							logger.info("***End of Unstable dependencies filtering***" + graph);
						}
					}
					if (_all || _parAS._HubLikeDependencies) {
						logger.info("***Start of Hub-Like dependencies detection***"+ graph);
						runHubLikeDependencies(graph);
						logger.info("***End of Hub-Like dependencies detection***"+ graph);
					}
					if (_all || _parMetric._PackageMetrics) {
						logger.info("***Start of Package Metrics Calculation***"+ graph);
						if(_packageMetricsCalculator==null){
							_packageMetricsCalculator = new PackageMetricsCalculator(graph);
						}
						List<Vertex> projectPackages = GraphUtils.findVerticesByLabel(graph, GraphBuilder.PACKAGE);
						File fileCsv = OutputDirUtils.getFileInOutputFolder(FILE_PACKAGE_METRICS);
						createCSVPackageMetrics(projectPackages, fileCsv);
						logger.info("***End of Package Metrics Calculation***"+ graph);
					}
					if (_all || _parMetric._ClassMetrics) {
						logger.info("***Start of Class Metrics Calculation***"+ graph);
						List<JavaClass> projectClasses = _sys.getClasses();
						if(_classMetricsCalculator==null){
							_classMetricsCalculator = new ClassMetricsCalculator(graph);
						}
						File fileCsv = OutputDirUtils.getFileInOutputFolder(FILE_CLASS_METRICS);
						createCSVClassesMetrics(fileCsv, projectClasses);
						logger.info("***End of Class Metrics Calculation***"+ graph);
					}

					
				}  				else {
					writeGraph();
				}

			} catch (NullPointerException e) {
				//				logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (IOException e) {
				//				logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (TypeVertexException e) {
				//				logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (FastNoSuchElementException e) {
				//				logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (Exception e) {
				//				logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} finally {
				closeGraph(graph);
			}
		}
	}



	private void prettyPrintOutput() {
		//		List<String> par = new ArrayList<String>();
		List<String> comm = new ArrayList<String>();
		Map<String,ParameterDescription> parSet = new HashMap<String,ParameterDescription>();
		Map<String,Map<String,ParameterDescription> > parComm = new HashMap<String,Map<String,ParameterDescription> >();
		for(ParameterDescription d:_k.getParameters()){
			if(!((Parameter)d.getParameter().getParameter()).hidden()){
				//				System.out.println(d.getNames()+" - "+d.getDescription());
				//				par.add(d.getNames());
				parSet.put(d.getNames(), d);
			}
		}
		_k.getCommands();
		for(String ks:_k.getCommands().keySet()){
			comm.add(ks);
			List<String> commPar = new ArrayList<String>();
			Map<String,ParameterDescription> commParSet = new HashMap<String,ParameterDescription>();
			for(ParameterDescription d:_k.getCommands().get(ks).getParameters()){
				//					System.out.println(d.getNames()+" - "+d.getDescription());
				if(!((Parameter)d.getParameter().getParameter()).hidden()){
					//					System.out.println(ks +" - "+ d.getNames()+" - "+d.getDescription());
					commPar.add(d.getNames());
					commParSet.put(d.getNames(), d);
				}
			}
			parComm.put(ks, commParSet);
		}
		//		par = Ordering.natural().sortedCopy(par);
		//		comm = Ordering.natural().sortedCopy(comm);
		System.out.println("Usage: java -jar Arcan.jar -p project_path [options] [command] [command options]");
		String s = "-projectFolder, -p";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("   Options:");
		s = "-all";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tArchitectural smells detection parameter:");
		s = "-CycleDependency, -CD, -cd";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-HubLikeDependencies, -HL, -hl";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-UnstableDependencies, -UD, -ud";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
//		s = "-filter, -F, -f";
//		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tMetrics computation:");
		s = "-ClassMetrics, -CM, -cm";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-PackageMetrics, -PM, -pm";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tProject read configuration parameter:");
		s = "-class, -CL, -cl";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-folderOfJars, -FJ, -fj";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-jar, -JR, -jr";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tNeo4j database parameter:");
		s = "-neo4j";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-neo4jDBFolder, -d";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tOutput folder of CSV files:");
		s = "-outputDir, -out";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tOther:");
		s = "-log, -verbose, -v";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		s = "-help, -h";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));

		//		for(String ks:comm){
		//			System.out.println(String.format("\tCommand:\n\t\t%s [option]",ks));
		//			for(String d:Ordering.natural().sortedCopy(parComm.get(ks).keySet())){
		//				//				System.out.println(ks+" - "+_k.getCommands().get(ks)+" - "+d+" - "+parComm.get(ks).get(d).getDescription());
		////				System.out.println(String.format("\t\t   %s\n\t\t\tDescription: %s",d,parComm.get(ks).get(d).getDescription()));
		//				System.out.println(String.format("\t\t   %s\n\t\t      Description: %s\n\t\t      Default: %s",d,parComm.get(ks).get(d).getDescription(),parComm.get(ks).get(d).getDefault()));
		//			}
		//		}
	}



	private void createCSVClassesMetrics(File fileCsv, List<JavaClass> projectClasses)
			throws IOException, TypeVertexException, NullPointerException {
		logger.debug("file csv metrics of classes: "+fileCsv);
		CSVFormat formatter = CSVFormat.EXCEL.withHeader("Class", "FI", "FO", "CBO", "LCOM");
		FileWriter writer = new FileWriter(fileCsv);
		CSVPrinter printer = new CSVPrinter(writer, formatter);
		for (JavaClass clazz : projectClasses) {
			String className = clazz.getClassName();
			int fanIn = _classMetricsCalculator.calculateFanIn(className);
			int fanOut = _classMetricsCalculator.calculateFanOut(className);
			int cbo = _classMetricsCalculator.calculateCBO(className);
			double lcom = _classMetricsCalculator.calculateLCOM(clazz);
			printer.print(className);
			printer.print(fanIn);
			printer.print(fanOut);
			printer.print(cbo);
			printer.print(lcom);
			printer.println();
		}
		printer.close();
		writer.close();
	}

	private void createCSVPackageMetrics(List<Vertex> projectPackages, File fileCsv)
			throws IOException, NoSuchElementException, TypeVertexException {
		logger.debug("file csv metrics of classes: "+fileCsv);
		CSVFormat formatter = CSVFormat.EXCEL.withHeader("Package", "CA", "CE", "RMI", "RMA", "RMD");
		FileWriter writer = new FileWriter(fileCsv);
		CSVPrinter printer = new CSVPrinter(writer, formatter);

		for (Vertex pkg : projectPackages) {
			logger.debug("pkg.property = "+pkg);
			logger.debug("pkg.property = "+pkg.property(GraphBuilder.PROPERTY_PACKAGE_TYPE));
			logger.debug("pkg.property = "+pkg.property(GraphBuilder.PROPERTY_PACKAGE_TYPE).value());
			if (GraphBuilder.SYSTEM_PACKAGE.equals(pkg.property(GraphBuilder.PROPERTY_PACKAGE_TYPE).value())) {
				double[] metrics;
				logger.debug("metrics calculator = "+_packageMetricsCalculator);
				metrics = _packageMetricsCalculator.calculateInternalPackageMetrics(pkg);
				String n = pkg.value(GraphBuilder.PROPERTY_NAME);
				logger.debug("pkg.property name = "+n);
				if ("".equals(n)) {
					n = GraphBuilder.DEFAULT_PACKAGE;
				}
				printer.print(n);
				for (Double m : metrics) {
					if (m != null) {
						printer.print(m);
					}
				}
				printer.println();
			}
		}
		printer.close();
		writer.close();
	}

	private void runHubLikeDependencies(Graph graph) throws TypeVertexException, IOException {

		//		runHubLikeDependencies(graph, Paths.get(_arcanSubfolder).toAbsolutePath().toFile(), FILE_HUB_LIKE);
		runHubLikeDependencies(graph, OutputDirUtils.getFileInOutputFolder(FILE_HUB_LIKE));
	}

	private void runHubLikeDependencies(Graph graph, File outputFilePath)
			throws TypeVertexException, IOException {
		File outputFileCSVPath = outputFilePath;
		if(_classMetricsCalculator==null){
			_classMetricsCalculator = new ClassMetricsCalculator(graph);
		}
		_hubLikeDetector = new HubLikeDetector(graph, _classMetricsCalculator);
		Map<String, List<Integer>> hubLikeClasses = _hubLikeDetector.detect();
		
		// update the graph if it is a Neo4J graph
		if (graph instanceof Neo4jGraph) {
			Neo4JGraphWriter graphW = new Neo4JGraphWriter();
			graphW.write(graph, false);
		}
		CSVFormat formatter = CSVFormat.EXCEL.withHeader("Class", "FanIn", "FanOut", "Total Dependences");
		FileWriter writer = new FileWriter(outputFileCSVPath);
		CSVPrinter printer = new CSVPrinter(writer, formatter);
		if (hubLikeClasses != null && !hubLikeClasses.isEmpty()) {
			for (Entry<String, List<Integer>> e : hubLikeClasses.entrySet()) {
				printer.print(e.getKey());
				printer.print(e.getValue().get(1));
				printer.print(e.getValue().get(2));
				printer.print(e.getValue().get(0));
				printer.println();
			}
		} else {
			logger.info("***No Hub like Dependency smell detected, nothing to print***");
		}
		printer.close();
		writer.close();
	}

	private void runUnstableDependencies(Graph graph) throws TypeVertexException, FileNotFoundException, IOException {
		if(_packageMetricsCalculator==null)_packageMetricsCalculator = new PackageMetricsCalculator(graph);
		_unstableDependencyDetector = new UnstableDependencyDetector(graph, _packageMetricsCalculator);

		UDUtils.cleanUDDetection(graph);
		MetricsUploader m = new MetricsUploader(graph);

		
		try {
			m.updateInstability();
			if (graph instanceof Neo4jGraph) {
				Neo4JGraphWriter graphW = new Neo4JGraphWriter();
				graphW.write(graph, false);
			}
		} catch (TypeVertexException e) {
			logger.debug(e.getMessage());
		}

		_unstableDependencyDetector.newDetect();

		// update the graph if it is a Neo4J graph
		if (graph instanceof Neo4jGraph) {
			Neo4JGraphWriter graphW = new Neo4JGraphWriter();
			graphW.write(graph, false);
		}

		Map<String, List<String>> smellMap = _unstableDependencyDetector.getSmellMap();
		logger.debug("Obtained smell map of UD");
		UDPrinter p = new UDPrinter(OutputDirUtils.getOutputFolder(), _unstableDependencyDetector,FILE_UNSTABLE_DEPENDECY);
		logger.debug("Created csv printer of UD");
		p.print(smellMap);
		logger.debug("UD csv printer");
		p.closeAll();
		logger.debug("Closed csv printer of UD");
	}

	private void runUnstableDependencyFilter(Graph graph) throws IOException {
		File f = OutputDirUtils.getOutputFolder();
		logger.debug("Obtained output folder: "+f);
		runUnstableDependencyFilter(graph, f, FILE_UNSTABLE_DEPENDECY_FILTERED_30);
	}

	private void runUnstableDependencyFilter(Graph graph, File outputFilePath, String csvfile) throws IOException {
		logger.debug("***Start unstable dependency filtering***" + graph);
		UDRateFilter filter = new UDRateFilter(graph);

		logger.debug("***Finished unstable dependency filtering***" + graph);
		logger.debug("***Start writing csv of unstable dependency filtering***" + graph);
		UDPrinter p2 = new UDPrinter(outputFilePath, _unstableDependencyDetector, csvfile);
		logger.debug("Created csv printer of UD");
		p2.print(filter.filter(30));
		logger.debug("UD csv printer");
		p2.closeAll();
		logger.debug("Closed csv printer of UD");
		logger.debug("***End of writing csv of unstable dependency filtering***" + graph);
	}

	private void runCycleDetector(Graph graph) {
		logger.debug("***Start Cycles detection***" + graph);
		_cycleDetector = new CyclicDependencyDetector(graph, OutputDirUtils.getOutputFolder());


		// String nomeprog =
		// _projectFolder.toString().substring(0,
		// _projectFolder.toString().length() - 2);
		// _cycleDetector.setFilename("");
		CDFilterUtils.cleanCDDetection(graph);
		_cycleDetector.detect();
		if (graph instanceof Neo4jGraph) {
			Neo4JGraphWriter graphW = new Neo4JGraphWriter();
			graphW.write(graph, false);
		}

		List<Vertex> classes = GraphUtils.findVerticesByLabel(graph, GraphBuilder.CLASS);
		List<Vertex> packages = GraphUtils.findVerticesByLabel(graph, GraphBuilder.PACKAGE);

		CyclePrinter printer = new PrintToMatrix(classes);
		CyclePrinter printer2 = new PrintToTable(classes);

		printer.initializePrint(OutputDirUtils.getOutputFolder(), GraphBuilder.CLASS);
		printer2.initializePrint(OutputDirUtils.getOutputFolder(), GraphBuilder.CLASS);

		printer.printCyclesFromGraph(graph, _cycleDetector.getListOfCycleSmells(GraphBuilder.CLASS));
		printer2.printCyclesFromGraph(graph, _cycleDetector.getListOfCycleSmells(GraphBuilder.CLASS));

		printer.closePrint();
		printer2.closePrint();

		CyclePrinter printer3 = new PrintToMatrix(packages);
		CyclePrinter printer4 = new PrintToTable(packages);

		printer3.initializePrint(OutputDirUtils.getOutputFolder(), GraphBuilder.PACKAGE);
		printer4.initializePrint(OutputDirUtils.getOutputFolder(), GraphBuilder.PACKAGE);

		printer3.printCyclesFromGraph(graph, _cycleDetector.getListOfCycleSmells(GraphBuilder.PACKAGE));
		printer4.printCyclesFromGraph(graph, _cycleDetector.getListOfCycleSmells(GraphBuilder.PACKAGE));

		printer3.closePrint();
		printer4.closePrint();

		logger.debug("***End of cycles detection***");
	}

	private void runCycleDetectorShapeFilter(Graph graph) {
		//		runCycleDetectorShapeFilter(graph, Paths.get(_arcanSubfolder + FILTERED_URL).toFile(), null);
		runCycleDetectorShapeFilter(graph, OutputDirUtils.getOutputFolder(), null);
	}

	private void runCycleDetectorShapeFilter(Graph graph, File outputFilePath, String nameFile) {
		logger.debug("***Start Cycles filtering***" + graph);

		CDFilterUtils.cleanCDShapeFilter(graph);

		CDShapeFilter filter = new CDShapeFilter(graph);

		filter.getCircleCycles(GraphBuilder.CLASS, PropertyEdge.LABEL_CLASS_DEPENDENCY.toString());
		filter.getCircleCycles(GraphBuilder.PACKAGE, GraphBuilder.LABEL_PACKAGE_AFFERENCE);
		logger.debug("Computed circle");

		filter.getCliqueCycles(GraphBuilder.CLASS, PropertyEdge.LABEL_CLASS_DEPENDENCY.toString());
		filter.getCliqueCycles(GraphBuilder.PACKAGE, GraphBuilder.LABEL_PACKAGE_AFFERENCE);
		logger.debug("Computed clique");

		filter.getStarAndChainCycles(GraphBuilder.CLASS);
		filter.getStarAndChainCycles(GraphBuilder.PACKAGE);
		logger.debug("Computed stars and chain");

		if (graph instanceof Neo4jGraph) {
			Neo4JGraphWriter graphW = new Neo4JGraphWriter();
			graphW.write(graph, false);
			logger.debug("Written Graph Neo4j");
		}

		CyclePrinter printerShape = new PrintShapesDocker(nameFile);
		printerShape.initializePrint(outputFilePath, GraphBuilder.CLASS);
		printerShape.printCyclesFromGraph(graph, GraphUtils.findVerticesByProperty(graph, GraphBuilder.CYCLE_SHAPE,
				GraphBuilder.PROPERTY_VERTEX_TYPE, GraphBuilder.CLASS));
		printerShape.closePrint();

		CyclePrinter printerShape2 = new PrintShapesDocker(nameFile);
		printerShape2.initializePrint(outputFilePath, GraphBuilder.PACKAGE);
		printerShape2.printCyclesFromGraph(graph, GraphUtils.findVerticesByProperty(graph, GraphBuilder.CYCLE_SHAPE,
				GraphBuilder.PROPERTY_VERTEX_TYPE, GraphBuilder.PACKAGE));
		printerShape2.closePrint();
		
		Map<Vertex, Set<Vertex>> cl = CDFilterUtils.getClassInvolved(graph);
		logger.debug("classe involved");
		for(Vertex v : cl.keySet()){
			System.out.println(v+",\tsmell package: "+v.value(GraphBuilder.PROPERTY_SHAPE_TYPE));
			for(Vertex m : cl.get(v)){
				System.out.println(m+",\tclass name: "+m.value(GraphBuilder.PROPERTY_NAME));
			}
		}
		
		logger.debug("***End of cycles filtering***");
	}





	private Graph readGraph() {
		logger.info("***Start Reading Neo4j***");
		logger.info("***Start Reading Neo4j*** - " + _parNeo4j._dbFolder.toPath());
		GraphReader reader = new GraphReader(_parNeo4j._dbFolder.toPath());
		logger.info("***Created Reader Neo4j***");
		Graph graph = reader.getGraph();
		logger.info("***Readed Neo4j***");
		logger.info("***Readed Neo4j*** - " + graph);
		return graph;
	}

	private Graph writeGraph() {
		return writeGraphNeo4j(_parNeo4j._dbFolder, _parProject._projectFolder);
	}

	private Graph writeGraphNeo4j(final File dbFolder, final File projectFolder) {
		logger.info("***Start graph building***");

		GraphBuilder graphB = null;
		GraphWriter graphW = null;
		if (!_parProject._jarsFolderMode && !_parProject._jarMode && _parProject._classMode) {
			_sys = new SystemBuilderByUrl();
		}
		if (_parProject._jarMode) {
			_sys = new SystemBuilderByJar();
		}
		if (_parProject._jarsFolderMode) {
			_sys = new SystemBuilderByFolderOfJars();
		}
		logger.info("***Start graph building*** - " + projectFolder.getAbsolutePath().toString());
		_sys.readClass(projectFolder.toString());
		graphB = new GraphBuilder(_sys.getClassesHashMap(), _sys.getPackagesHashMap());
		graphW = new Neo4JGraphWriter();
		logger.info("***Start Writing Neo4j***");
		logger.info("***Start Writing Neo4j*** - " + dbFolder.toPath());
		graphW.setup(dbFolder.toPath().toAbsolutePath().toString());
		Graph graph = graphW.init();
		logger.info("***Graph initializated***");
		logger.info("***Graph initializated*** - graph:   " + graph);
		logger.info("***Graph initializated*** - builder: " + graphB);
		graphB.createGraph(graph);
		logger.info("***Graph readed from compiled file***");
		graphW.write(graph, false);
		logger.info("***End of graph building***");
		return graph;
	}

	private Graph writeGraphTinkerpop(final File projectFolder) {
		logger.info("***Start graph building***");
		_sys = null;
		GraphBuilder graphB = null;
		// GraphWriter graphW = null;
		if (!_parProject._jarsFolderMode && !_parProject._jarMode && _parProject._classMode) {
			_sys = new SystemBuilderByUrl();
		}
		if (_parProject._jarMode) {
			_sys = new SystemBuilderByJar();
		}
		if (_parProject._jarsFolderMode) {
			_sys = new SystemBuilderByFolderOfJars();
		}
		logger.info("***Start graph building*** - " + projectFolder.getAbsolutePath().toString());
		_sys.readClass(projectFolder.toString());
		graphB = new GraphBuilder(_sys.getClassesHashMap(), _sys.getPackagesHashMap());
		// sys = null;
		// graphW = new Neo4JGraphWriter();
		logger.info("***Start Opening Tinkerpop***");
		// logger.info("***Start Opening Tinkerpop*** - "+dbFolder.toPath());
		// graphW.setup(dbFolder.toPath().toAbsolutePath().toString());
		Graph graph = TinkerGraph.open();
		logger.info("***Graph initializated***");
		logger.info("***Graph initializated*** - graph:   " + graph);
		logger.info("***Graph initializated*** - builder: " + graphB);
		graphB.createGraph(graph);
		logger.info("***Graph created from compiled file***");
		logger.info("***Graph created from compiled file*** - graph:" + graph);
		// graphW.write(graph, false);
		logger.info("***End of graph building***");
		return graph;
	}

	public void closeGraph(final Graph graph) {
		try {
			if (graph != null) {
				graph.close();
			}
		} catch (Exception e) {
			//			logger.error(e.getMessage(), e.fillInStackTrace());
			e.printStackTrace();
		}
	}

}
