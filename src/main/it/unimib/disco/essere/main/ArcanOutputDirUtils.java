package it.unimib.disco.essere.main;

import java.io.File;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArcanOutputDirUtils {
	private static final Logger logger = LogManager.getLogger(OutputDirUtils.class);

	private static String _arcanSubfolder;
	private static String _arcanSubfolderFilter;
	public static final String ARCAN_OUTPUT_URL = File.separator + "ArcanOutput";
	public static final String FILTERED_URL = File.separator + "filtered";

	public static void createDir(final File projectFolder) {
		createDir(projectFolder, false);
	}

	public static void createDir(final File projectFolder, final boolean singleFile) {
		createDir(projectFolder, ARCAN_OUTPUT_URL, singleFile);
	}
	
	public static void createDir(final File projectFolder, final String output_url, final boolean singleFile) {
		if (singleFile) {
			_arcanSubfolder = projectFolder.getParentFile().getAbsolutePath() + output_url;
			createOutputDir(_arcanSubfolder);
		} else {
			_arcanSubfolder = projectFolder + output_url;
			createOutputDir(_arcanSubfolder);
		}
	}
	
	public static void createDirWithSubDirFilters(final File projectFolder) {
		createDirWithSubDirFilters(projectFolder, false);
	}

	public static void createDirWithSubDirFilters(final File projectFolder, final boolean singleFile) {
		createDirWithSubDirFilters(projectFolder, FILTERED_URL, ARCAN_OUTPUT_URL, singleFile);
	}
	
	public static void createDirWithSubDirFilters(final File projectFolder, final String arcan_output_url, final boolean singleFile) {
		createDirWithSubDirFilters(projectFolder, FILTERED_URL, arcan_output_url, singleFile);
	}
	
	public static void createDirWithSubDirFilters(final File projectFolder, final String filter_url, final String arcan_output_url, final boolean singleFile) {
		if (singleFile) {
			_arcanSubfolder = projectFolder.getParentFile().getAbsolutePath() + arcan_output_url;
			_arcanSubfolderFilter = _arcanSubfolder + filter_url;
			createOutputDir(_arcanSubfolder);
			createOutputDir(_arcanSubfolderFilter);
		} else {
			_arcanSubfolder = projectFolder.getAbsolutePath() + arcan_output_url;
			_arcanSubfolderFilter = _arcanSubfolder + filter_url;
			createOutputDir(_arcanSubfolder);
			createOutputDir(_arcanSubfolderFilter);
		}
	}
	
	public static void createOutputDir(final String directoryName, final boolean singleFile) {
		if(singleFile){
			createOutputDir(directoryName);
		}else{
			createOutputDirSingleFile(directoryName);
		}
	}

	public static void createOutputDir(final String directoryName) {
		File theDir = new File(directoryName);
		createOutputDir(theDir);
	}

	private static void createOutputDirSingleFile(String directoryName) {
		File theDir = new File(directoryName);
		File f = theDir.getParentFile().getParentFile();
		logger.debug("parent directory of the jar: " + f + " name:" + theDir.getName());
		f = Paths.get(f.getAbsolutePath(), theDir.getName()).toFile();
		logger.debug("output arcan: " + f);
		createOutputDir(f);
	}

	public static void createOutputDir(File theDir) {
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			logger.debug("creating directory: " + theDir.getName());
			boolean result = false;
			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException e) {
				logger.error(e.getMessage());
			}
			if (result) {
				logger.debug("DIR created");
			}
		}
	}
	
	public static File getFileInOutputFolder(final String fileName){
		File f = Paths.get(_arcanSubfolder, fileName).toAbsolutePath().toFile();
		return f;
	}
	
	public static File getArcanOutput(){
		return Paths.get(_arcanSubfolder).toAbsolutePath().toFile();
	}
	public static File getArcanFilteredOutput(){
		return Paths.get(_arcanSubfolderFilter).toAbsolutePath().toFile();
	}
}