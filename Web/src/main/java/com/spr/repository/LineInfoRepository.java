package com.spr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spr.model.FileInfo;
import com.spr.model.LineInfo;

public interface LineInfoRepository extends JpaRepository<LineInfo, Integer> {

}
