package com.spr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spr.model.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {

}
