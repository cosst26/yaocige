package com.company.project.service;
import com.company.project.model.MomentComment;
import com.company.project.core.Service;

import java.util.List;

public interface MomentCommentService extends Service<MomentComment> {

    List<MomentComment> selectByMomentId(Long momentId);
}
