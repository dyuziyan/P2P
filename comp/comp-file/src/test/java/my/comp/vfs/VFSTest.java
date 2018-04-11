package my.comp.vfs;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.ftp.FtpFileObject;
import org.junit.Test;

public class VFSTest {

	@Test
	public void test1() throws Exception {

		FtpFileObject file = (FtpFileObject) VFS.getManager().resolveFile(
				"ftp://mall:wxMall2345@112.74.209.129:21/wx/mall/goods/test.jpg");
		if (!file.exists()) file.createFile();

		File src = new File("D:/test.jpg");
		IOUtils.copy(new FileInputStream(src), file.getContent().getOutputStream());
		file.close();
	}

	@Test
	public void test2() throws Exception {

		FTPClient ftp = new FTPClient();
		ftp.connect("112.74.209.129", 2100);
		ftp.login("mall", "wxMall2345");

		System.out.println(ftp.getReplyCode());

		FTPFile[] files = ftp.listFiles();

		for (FTPFile file : files) {
			System.out.println(file.getName());
		}

		System.out.println(ftp.changeWorkingDirectory("wx"));

		files = ftp.listFiles();
		for (FTPFile file : files) {
			System.out.println(file.getName());
		}
	}

	@Test
	public void test3() throws Exception {

		FTPClient ftp = new FTPClient();
		ftp.connect("192.168.1.200", 2100);
		ftp.login("upuser", "123456789");

		System.out.println(ftp.getReplyCode());

		FTPFile[] files = ftp.listFiles();

		for (FTPFile file : files) {
			System.out.println(file.getName());
		}
		System.out.println("wx=================================================");
		System.out.println(ftp.changeWorkingDirectory("wx"));

		files = ftp.listFiles();
		for (FTPFile file : files) {
			System.out.println(file.getName());
		}
	}

	@Test
	public void test4() throws Exception {
		System.out.println(System.currentTimeMillis());
		FTPClient ftp = new FTPClient();
		ftp.connect("112.74.114.219", 21);
		ftp.login("mall", "wxMall2345");
		// ftp.enterLocalPassiveMode();
		System.out.println(ftp.getReplyCode());

		FTPFile[] files = ftp.listFiles();

		for (FTPFile file : files) {
			System.out.println(file.getName());
		}

		System.out.println(ftp.changeWorkingDirectory("wx"));

		files = ftp.listFiles();
		for (FTPFile file : files) {
			System.out.println(file.getName());
		}
		System.out.println(System.currentTimeMillis());
	}

	@Test
	public void test5() throws Exception {

		FtpFileObject file = (FtpFileObject) VFS.getManager().resolveFile("ftp://mall:wxMall2345@112.74.114.219:21/aaa/test.txt");
		if (!file.exists()) file.createFile();

		File src = new File("D:/test.txt");
		IOUtils.copy(new FileInputStream(src), file.getContent().getOutputStream());
		file.close();
	}

}
