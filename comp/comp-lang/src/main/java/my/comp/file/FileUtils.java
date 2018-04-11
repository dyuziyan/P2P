package my.comp.file;

public class FileUtils {
	
	public static String convertFileSize(long size){
		if(size/(1024*1024*1024)>=1){
			return size/(1024*1024*1024)+" GB";
		}else if(size/(1024*1024)>=1){
			return size/(1024*1024)+" MB";
		}else if(size/1024>1){
			return size/1024+" KB";
		}else if(size>0){//小于1KB通用1KB
			return "1.0 KB";
		}else{
			return "0 KB";
		}
	}
}
