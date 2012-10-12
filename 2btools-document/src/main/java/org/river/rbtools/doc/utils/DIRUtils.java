package org.river.rbtools.doc.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * the directory Utils class
 * @author river
 * @create 20120611
 */
public class DIRUtils {
	public static final String URL_SEPERATOR="/";
	
	/**
	 * build the path list
	 * @param path
	 * @return
	 */
	public static List<String> subPaths(String path){
		if(StringUtils.isEmpty(path)) throw new NullPointerException("path is empty");
		List<String> subPaths=new ArrayList<String>();
		subPaths.add(path);
		String parent=null;
		while((parent=parentPath(path))!=null){
			subPaths.add(parent);
		}
		return subPaths;
	}
	
	/**
	 * get the parent path
	 * @param path
	 * @return
	 */
	public static String parentPath(String path){
		if(StringUtils.isEmpty(path))return null;
		if(path.endsWith(URL_SEPERATOR)){
			path=path.substring(0,path.length()-1);
		}
		int i=path.lastIndexOf(URL_SEPERATOR);

		String parent=null;
		if(i>0){
			parent=path.substring(0, i);
		}
		return parent;
	}
	
	public static String getDIRName(String path){
		if(StringUtils.isEmpty(path))return null;
		if(path.endsWith(URL_SEPERATOR)){
			path=path.substring(0,path.length()-1);
		}
		int i=path.lastIndexOf(URL_SEPERATOR);
		String name=null;
		if(i>0&&i<path.length()){
			name=path.substring(i+1);
		}else{
			name=path;
		}
		return name;
	}
}
