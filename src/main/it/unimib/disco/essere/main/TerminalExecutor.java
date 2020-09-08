package it.unimib.disco.essere.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tinkerpop.gremlin.process.traversal.util.FastNoSuchElementException;
import org.apache.tinkerpop.gremlin.structure.Graph;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterDescription;
import com.beust.jcommander.ParametersDelegate;

import it.unimib.disco.essere.main.graphmanager.TypeVertexException;
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

public class TerminalExecutor {

	static final Logger logger = LogManager.getLogger(TerminalExecutor.class);

	@Parameter(names = { "-log", "-verbose",
			"-v" }, description = "Level of verbosity", validateWith = PositiveInteger.class, descriptionKey = "verbose")
	private Integer _verbose = 0;

	@Parameter(names = { "-help", "-h" }, help = true, description = "Print this help", descriptionKey = "help")
	private boolean _help = false;

	@ParametersDelegate
	private static ParametersComputeMetricsTerminal _parMetric = ParametersComputeMetricsTerminal.getInstance();

	@ParametersDelegate
	private static ParametersDetectionArchitecturalSmell _parAS = new ParametersDetectionArchitecturalSmell();
	// private final static ParametersAllValue _parAll = new ParametersAllValue();

	@Parameter(names = {
			"-all" }, description = "Calculates all metrics and all type of architectural smells", descriptionKey = "detection")
	public boolean _all = false;

	@ParametersDelegate
	private static ParametersNeo4jDBTerminal _parNeo4j = ParametersNeo4jDBTerminal.getInstance();

	@ParametersDelegate
	private static ParameterInputProjectInputTerminal _parProject = ParameterInputProjectInputTerminal.getInstance();

	@Parameter(names = { "-filter", "-F",
			"-f" }, hidden = true, description = "Filter the results of the smells detection", descriptionKey = "detection")
	private boolean _filter = true;

	@Parameter(names = { "-outputDir",
			"-out" }, description = "output dir of results", converter = FileConverter.class, validateWith = ExistFile.class, descriptionKey = "output")
	private File _outDir = null;

	// FIXME below are all hidden parameters
	@Parameter(names = { "-classpathFolder",
			"-c" }, hidden = true, description = "Classpath system (folder of the system jar files)", converter = FileConverter.class, validateWith = ExistFile.class)
	File _classPathFolder = null;

	@Parameter(names = { "-libraryFolder",
			"-l" }, hidden = true, description = "Library folder (folder of library jar files)", converter = FileConverter.class, validateWith = ExistFile.class)
	File _libFolder = null;

	@ParametersDelegate
	ParameterGitValueTerminal _parGit = new ParameterGitValueTerminal();

	@Parameter(names = "-cycleTest", hidden = true, description = "test")
	private boolean _cycleTest = false;

	@Parameter(names = { "-javaversion",
			"-jv" }, hidden = true, description = "Version of JVM, e.g.,1.8,1.7.", converter = JavaVersionConverter.class, validateWith = ExistJavaVersion.class)
	private String _jv = JavaVersionConverter._j8;

	static JCommander _k = null;

	// private static final String FILE_HUB_LIKE = "HL.csv";
	// private static final String FILE_CYCLE = "CL.csv";
	// private static final String FILE_UNSTABLE_DEPENDECY = "UD.csv";
	// private static final String FILE_UNSTABLE_DEPENDECY_FILTERED_30 = "UD30.csv";
	// private static final String FILE_PACKAGE_METRICS = "PM.csv";
	// private static final String FILE_CLASS_METRICS = "CM.csv";

	/**
	 * It required pass the string of the complete path of the project folder input:
	 * -p
	 * "C:\\Users\\ricca\\workspaceThinkerpop\\ToySystem\\target\\classes\\it\\unimib\\disco\\essere\\toysystem"
	 * 
	 * @author RR
	 * @param args
	 */
	public static void main(String[] args) {
		org.apache.logging.log4j.core.config.Configurator.setRootLevel(Level.INFO);
		logger.info("***Start of Terminal Executor***");
		TerminalExecutor tt = new TerminalExecutor();
		_k = new JCommander(tt);
		_k.setCaseSensitiveOptions(false);
		_k.setProgramName("java -jar Arcan.jar");
		// _k.addCommand("metric",_parMetric);
		// _k.addCommand("as",_parAS);
		// _k.addCommand("all",_parAll);
		// _k.addCommand("neo4j",_parNeo4j);
		_k.parse(args);
		logger.info("*** args:" + args.length + "***");
		try {
			tt.run(args);
		} catch (TypeVertexException e1) {
			// logger.error(e1.getMessage()+e1.fillInStackTrace(), e1.fillInStackTrace());
			e1.printStackTrace();
		} catch (Exception e) {
			// logger.error(e.getLocalizedMessage()+e.fillInStackTrace().getStackTrace(),
			// e.fillInStackTrace());
			// logger.catching(e.fillInStackTrace());
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
			// _k.usage();

			prettyPrintOutput();

		} else {
			Graph graph = null;
			try {
				if (_parGit._printGitCommit) {
					IXPDDetectionUtils.printCommitHisory(_parGit._gitFolder);
				} else if (_parGit._gitHistory) {
					graph = IXPDDetectionUtils.writeGraphHistoryAndDetectImplicitCrossPackageDependency(_parNeo4j,
							_parGit._dbGitFolder, _parGit._gitFolder, _outDir);
				} else if ((_parAS._cycle || _parAS._UnstableDependencies || _parAS._HubLikeDependencies
						|| _parMetric._PackageMetrics || _parMetric._ClassMetrics || _all)) {
					InterfaceModel model = new InterfaceModel();
					model.set_classMode(_parProject._classMode);
					model.set_jarMode(_parProject._jarMode);
					model.set_jarsFolderMode(_parProject._jarsFolderMode);
					model.setOutputFolder(_outDir);
					model.setDbFolder(_parNeo4j._dbFolder);
					if (_parProject._projectFolder != null) {
						model.setProjectFolder(_parProject._projectFolder);
						if (_parNeo4j._writeNeo4j) {
							model.buildProjectNeo4J();
						} else {
							model.buildGraphTinkerpop();
						}
					} else if (!_parNeo4j._dbFolder.exists() || _parNeo4j._dbFolder.getTotalSpace() <= 0) {
						throw new IOException("Folder doesn't exist or it's empty.");
					} else {
						model.readGraph();
					}

					// if (_outDir != null) {
					// logger.info("***Output folder creating***"+File.separator+_outDir.getName());
					// OutputDirUtils.createDirFullPath(_outDir, _parProject._jarMode);
					// logger.info("***Output folder created***"+OutputDirUtils.getOutputFolder());
					// }
					// else{
					// logger.info("***Output folder creating***"+File.separator);
					// OutputDirUtils.createDirFullPath(_parProject._jarMode);
					// logger.info("***Output folder created***"+OutputDirUtils.getOutputFolder());
					// }
					model.createOutPutFolderTerminal();

					if (_all || _parAS._cycle) {
						logger.debug("***Start Cycle detection***" + graph);
						model.runCycleDetector();
						logger.debug("***End of Cycle detection***" + graph);
						if (_filter) {
							logger.debug("***Start Cycle filtering***" + graph);
							model.runCycleDetectorShapeFilter();
							logger.debug("***End of Cycle filtering***" + graph);
						}
					}
					if (_all || _parAS._UnstableDependencies) {
						logger.debug("***Start Unstable dependencies detection***" + graph);
						model.runUnstableDependencies();
						logger.debug("***End of Unstable dependencies detection***" + graph);
						if (_filter) {
							logger.debug("***Start Unstable dependencies filtering***" + graph);
							model.runUnstableDependencyFilter();
							// runUnstableDependencyFilter(graph);
							logger.debug("***End of Unstable dependencies filtering***" + graph);
						}
					}
					if (_all || _parAS._HubLikeDependencies) {
						logger.debug("***Start of Hub-Like dependencies detection***" + graph);
						model.runHubLikeDependencies();
						logger.debug("***End of Hub-Like dependencies detection***" + graph);
					}
					if (_all || _parMetric._PackageMetrics) {
						logger.debug("***Start of Package Metrics Calculation***" + graph);
						model.createCSVPackageMetrics();
						logger.debug("***End of Package Metrics Calculation***" + graph);
					}
					if (_all || _parMetric._ClassMetrics) {
						logger.debug("***Start of Class Metrics Calculation***" + graph);
						model.createCSVClassesMetrics();
						logger.debug("***End of Class Metrics Calculation***" + graph);
					}

				} else {
					// in this case should be passed a graph database in order to be readed
					InterfaceModel model = new InterfaceModel();
					model.set_classMode(_parProject._classMode);
					model.set_jarMode(_parProject._jarMode);
					model.set_jarsFolderMode(_parProject._jarsFolderMode);
					model.setOutputFolder(_outDir);
					model.setDbFolder(_parNeo4j._dbFolder);
					if (_parProject._projectFolder != null) {
						model.setProjectFolder(_parProject._projectFolder);
						if (_parNeo4j._writeNeo4j) {
							model.buildProjectNeo4J();
						} else {
							model.buildGraphTinkerpop();
						}
					} else if (!_parNeo4j._dbFolder.exists() || _parNeo4j._dbFolder.getTotalSpace() <= 0) {
						throw new IOException("Folder doesn't exist or it's empty.");
					} else {
						model.readGraph();
					}
				}

			} catch (NullPointerException e) {
				// logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();

			} catch (IOException e) {
				// logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();

			} catch (TypeVertexException e) {
				// logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();

			} catch (FastNoSuchElementException e) {
				// logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (Exception e) {
				// logger.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} finally {
				InterfaceModel.closeGraph(graph);
			}
		}
	}

	private void prettyPrintOutput() {
		// List<String> par = new ArrayList<String>();
		// List<String> comm = new ArrayList<String>();
		Map<String, ParameterDescription> parSet = new HashMap<String, ParameterDescription>();
		// Map<String,Map<String,ParameterDescription> > parComm = new
		// HashMap<String,Map<String,ParameterDescription> >();
		for (ParameterDescription d : _k.getParameters()) {
			if (!((Parameter) d.getParameter().getParameter()).hidden()) {
				// System.out.println(d.getNames()+" - "+d.getDescription());
				// par.add(d.getNames());
				parSet.put(d.getNames(), d);
			}
		}
		// for(String ks:_k.getCommands().keySet()){
		// comm.add(ks);
		// List<String> commPar = new ArrayList<String>();
		// Map<String,ParameterDescription> commParSet = new
		// HashMap<String,ParameterDescription>();
		// for(ParameterDescription d:_k.getCommands().get(ks).getParameters()){
		// // System.out.println(d.getNames()+" - "+d.getDescription());
		// if(!((Parameter)d.getParameter().getParameter()).hidden()){
		// // System.out.println(ks +" - "+ d.getNames()+" - "+d.getDescription());
		// commPar.add(d.getNames());
		// commParSet.put(d.getNames(), d);
		// }
		// }
		// parComm.put(ks, commParSet);
		// }
		// par = Ordering.natural().sortedCopy(par);
		// comm = Ordering.natural().sortedCopy(comm);
		System.out.println("Usage: java -jar Arcan.jar -p project_path [options] [command] [command options]");
		String s = "-projectFolder, -p";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		System.out.println("   Options:");
		s = "-all";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		System.out.println("\tArchitectural smells detection parameter:");
		s = "-CycleDependency, -CD, -cd";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-HubLikeDependencies, -HL, -hl";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-UnstableDependencies, -UD, -ud";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		// TODO when is hidde this must be commented
		// s = "-filter, -F, -f";
		// System.out.println(String.format("\t\t%s\n\t\t Description: %s\n\t\t Default:
		// %s",s,parSet.get(s).getDescription(),parSet.get(s).getDefault()));
		System.out.println("\tMetrics computation:");
		s = "-ClassMetrics, -CM, -cm";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-PackageMetrics, -PM, -pm";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		System.out.println("\tProject read configuration parameter:");
		s = "-class, -CL, -cl";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-folderOfJars, -FJ, -fj";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-jar, -JR, -jr";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		System.out.println("\tNeo4j database parameter:");
		s = "-neo4j";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-neo4jDBFolder, -d";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		System.out.println("\tOutput folder of CSV files:");
		s = "-outputDir, -out";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		System.out.println("\tOther:");
		s = "-log, -verbose, -v";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));
		s = "-help, -h";
		System.out.println(String.format("\t\t%s\n\t\t   Description: %s\n\t\t   Default: %s", s,
				parSet.get(s).getDescription(), parSet.get(s).getDefault()));

	}

}
