package com.coderby.myapp.files.dao;

import java.util.HashMap;
import java.util.List;

import com.coderby.myapp.files.model.FileVO;

public interface IFileRepository {
	
	public int getMaxFileId();
	public void uploadFile(FileVO file);
	
	public List<FileVO> getFileList(String directoryName);	
	public List<FileVO> getAllFileList();
	public List<FileVO> getImageList(String directoryName);
	
	public FileVO getFile(int fileId);
	
	public String getDirectoryName(int fileId);
	public void updateDirectory(HashMap<String, Object> map);
	public void deleteFile(int fileId);
	public void updateFile(FileVO file);
	
}
