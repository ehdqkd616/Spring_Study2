package com.coderby.myapp.files.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coderby.myapp.files.model.FileVO;

public interface IFileRepository {
	
	public int getMaxFileId();
	public void uploadFile(FileVO file);
	
	public List<FileVO> getFileList(@Param("directoryName")String directoryName);	
	public List<FileVO> getAllFileList();
	public List<FileVO> getImageList(@Param("directoryName")String directoryName);
	
	public FileVO getFile(@Param("fileId")int fileId);
	
	public String getDirectoryName(@Param("fileId")int fileId);
	public void updateDirectory(HashMap<String, Object> map);
	public void deleteFile(@Param("fileId")int fileId);
	public void updateFile(FileVO file);
	
}
