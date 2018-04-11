package my.comp.vfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import my.comp.lang.UuidUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-vfs.xml" })
public class WFSTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testLocal() throws FileSystemException {
		FileObject file = WFS.resolveFile("D://test/set");

		if (!file.exists()) {
			file.createFolder();
		}
		;

	}

	@Test
	public void testFtp() throws FileSystemException {
		FileObject file = VFS.getManager().resolveFile("ftp://upuser:123456789@192.168.1.200:2100/sqb2/2013/test.jsp");
		if (!file.exists()) {
			file.createFile();
		}
		;
	}

	@Test
	public void test() throws FileNotFoundException, IOException {
		FileObject file = WFS.resolveFile("mall://goods/test.jpg");

		if (!file.exists()) {
			file.createFile();
		}
		;

		File src = new File("D:/test.jpg");
		IOUtils.copy(new FileInputStream(src), file.getContent().getOutputStream());
		file.close();
	}

	public InputStream getInputStream(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}

	@Test
	public void testFileCopy() {
		String srcFilaPathAndName = "d:/test.jpg";
		File srcFile = new File(srcFilaPathAndName);
		// FileObject destFileObj = WFS.resolveFile("sqb://photo");
		File destDir = new File("d:/wx");
		try {
			FileUtils.copyFileToDirectory(srcFile, destDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFileCopy2() {
		String srcFilaPathAndName = "d:/test.jpg";
		InputStream in = this.getInputStream(srcFilaPathAndName);

		try {
			org.apache.commons.io.FileUtils.copyInputStreamToFile(in, new File("d:/wx", UuidUtils.getUuidTrimHyphen() + "jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
