package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.MomentComment;

import java.util.List;

public interface MomentCommentMapper extends Mapper<MomentComment> {

    List<MomentComment> selectByMomentId(Long momentId);

}