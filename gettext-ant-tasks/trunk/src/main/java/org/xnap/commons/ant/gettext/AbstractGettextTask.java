package org.xnap.commons.ant.gettext;

import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;

public class AbstractGettextTask extends Task {

    /**
     * PO directory.
     */
    protected String poDirectory;
    public void setPoDirectory(String poDirectory) {
        this.poDirectory = poDirectory;
    }

    /**
     * Filename of the .pot file
     */
    protected String keysFile = "keys.pot";
    public void setKeysFile(String keysFile) {
        this.keysFile = keysFile;
    }
    
    protected int runCommandLineAndWait(Commandline cl) {
    	try {
    		Process p = Runtime.getRuntime().exec(cl.getCommandline());
    		int exitCode = p.waitFor();
    		if (exitCode != 0) {
            	log(cl.getExecutable() + " returned " + exitCode);
            }
    		return exitCode;
    	} catch (IOException e) {
    		log("Could not execute " + cl.getExecutable() + ": " + e.getMessage(), Project.MSG_ERR);
    	} catch (InterruptedException e) {
    		log("Process was interrupted: " + e.getMessage(), Project.MSG_ERR);
    	}
    	return -1;
    }
}