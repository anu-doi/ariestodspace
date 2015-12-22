/**
 * 
 */
package au.edu.anu.ariestodspace.staging.sword.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.httpclient.methods.InputStreamRequestEntity;

/**
 * @author Rahul Khanna
 *
 */
public class BitstreamInfo {
	
	private Path file;
	private String fileNameToDeposit;

	public BitstreamInfo(String filepath) throws FileNotFoundException {
		this(Paths.get(filepath));
	}

	public BitstreamInfo(Path file) throws FileNotFoundException {
		if (!Files.isRegularFile(file)) {
			throw new FileNotFoundException("File " + file.toString() + " doesn't exist");
		}
		this.file = file;
	}

	public BitstreamInfo(String filepath, String fileNameToDeposit) throws FileNotFoundException {
		this(Paths.get(filepath), fileNameToDeposit);
	}

	public BitstreamInfo(Path file, String fileNameToDeposit) throws FileNotFoundException {
		if (!Files.isRegularFile(file)) {
			throw new FileNotFoundException("File " + file.toString() + " doesn't exist");
		}
		this.file = file;
		this.fileNameToDeposit = fileNameToDeposit;
	}

	public String getFilepath() {
		return file.toAbsolutePath().toString();
	}

	public Path getFile() {
		return file;
	}
	
	public String getFilename() {
		return file.getFileName().toString();
	}
	
	public String getFileNameToDeposit() throws UnsupportedEncodingException {
		String filename = fileNameToDeposit;
		if (filename == null) {
			filename = file.getFileName().toString();
		}
		filename = URLEncoder.encode(filename, "UTF-8");
		return filename;
	}
	
	public long getSize() {
		try {
			return Files.size(file);
		} catch (IOException e) {
			return InputStreamRequestEntity.CONTENT_LENGTH_AUTO;
		}
	}
	
	@Override
	public String toString() {
		return getFilepath();
	}
}
