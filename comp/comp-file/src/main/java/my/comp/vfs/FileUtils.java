package my.comp.vfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs2.FileNotFoundException;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.comp.config.SysConf;

public class FileUtils {
	
	private static FileSystemManager fsManager = null;
	// 静态处理块
	static {
	     try {
	           fsManager = VFS.getManager();
	     }catch(FileSystemException e) {
	           e.printStackTrace();
	     }
	 }
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public final static String FTP_DOWNLOAD_PATH = SysConf.get("file.root.http");
	
	public String uploadFile(String fileName, String filePath, String uplaodPath) {
		FileObject file;
		try {
			file = WFS.resolveFile(uplaodPath);
			if (!file.exists()) {
				try {
					file.createFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			File localFile = new File(filePath);
			FileInputStream inputStream = new FileInputStream(localFile);
			int rows = IOUtils.copy(inputStream, file.getContent().getOutputStream());
			WFS.closeQuietly(file);
			if (rows > 0) {
				// 获取文件上传全路径
				org.apache.commons.io.FileUtils.deleteQuietly(localFile);
				return FTP_DOWNLOAD_PATH + "/" + "wx/" + fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadFile {} to error", fileName, uplaodPath, e);
		}
		return null;
	}
	
	
	 /**
     * 读取文件内容
     */
    public static String readFileToString(String filePath, String encoding)throws IOException {
        if (StringUtils.isEmpty(filePath)){
            throw new IOException("File '"+ filePath + "' is empty.");
        }
        FileObject fileObj = null;
        InputStream in = null;
        try {
            fileObj= fsManager.resolveFile(filePath);
            if (fileObj.exists()) {
                if (FileType.FOLDER.equals(fileObj.getType())){
                    throw new IOException("File '"+ filePath+"' exists but is a directory");
                }else{
                    in= fileObj.getContent().getInputStream();
                    return IOUtils.toString(in,encoding);
                    // 返回List<String>，通过 IOUtils.readLines(in, encoding)的形式；
                    // 返回byte[]，通过IOUtils.toByteArray(in)的形式;
                }
            }else{
                throw new FileNotFoundException("File '"+ filePath +"' does not exist");
            }
        }catch(FileSystemException e) {
            throw new IOException("File '"+ filePath + "' resolveFile fail.");
        }finally{
            IOUtils.closeQuietly(in);
            if (fileObj != null) {
                fileObj.close();
            }
        }
    }
    
    /**
     * 读取文件内容
     */
    public static List<String> readFileToList(String filePath, String encoding)throws IOException {
        if (StringUtils.isEmpty(filePath)){
            throw new IOException("File '"+ filePath + "' is empty.");
        }
        FileObject fileObj = null;
        InputStream in = null;
        try {
            fileObj= fsManager.resolveFile(filePath);
            if (fileObj.exists()) {
                if (FileType.FOLDER.equals(fileObj.getType())){
                    throw new IOException("File '"+ filePath+"' exists but is a directory");
                }else{
                    in= fileObj.getContent().getInputStream();
                    return IOUtils.readLines(in, encoding);
                }
            }else{
                throw new FileNotFoundException("File '"+ filePath +"' does not exist");
            }
        }catch(FileSystemException e) {
            throw new IOException("File '"+ filePath + "' resolveFile fail.");
        }finally{
            IOUtils.closeQuietly(in);
            if (fileObj != null) {
                fileObj.close();
            }
        }
    }
    
    /**
     * 将内容写入文件中,如果文件不存在，那么创建
     */
    public static void writeStringToFile(String filePath, String data,String encoding) throws IOException {
        if (StringUtils.isEmpty(filePath)){
            throw new IOException("File '"+ filePath + "' is empty.");
        }
        FileObject fileObj = null;
        OutputStream out = null;
        try {
            fileObj= fsManager.resolveFile(filePath);
            if (!fileObj.exists()) {
                fileObj.createFile();
            }else{
                if (FileType.FOLDER.equals(fileObj.getType())){
                    throw new IOException("Write fail. File '"+ filePath+"' exists but is a directory");
                }
            }
            // 处理文件不可写的异常
            if(!fileObj.isWriteable()) {
                throw new IOException("Write fail. File '"+ filePath+"' exists but isWriteable is false.");
            }
            out= fileObj.getContent().getOutputStream();
            // commons io里的方法
            IOUtils.write(data,out, encoding);
        }catch(FileSystemException e) {
            throw new IOException("File '"+ filePath + "' resolveFile fail.");
        }finally{
            IOUtils.closeQuietly(out);
            if (fileObj != null) {
                fileObj.close();
            }
        }
    }
    
    /**
     * 修改文件的最后修改时间,将文件做旧
     */

    public static void changeLastModificationTime(String filePath,String lastTime) throws Exception {
        if (StringUtils.isEmpty(filePath)|| StringUtils.isEmpty(lastTime)) {
            throw new IOException("File '"+ filePath + " or "+ lastTime+"' is empty.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        long lastModifyTime =sdf.parse(lastTime).getTime();
        FileObject fileObj = null;
        try {
            fileObj= fsManager.resolveFile(filePath);
            fileObj.getContent().setLastModifiedTime(lastModifyTime);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
