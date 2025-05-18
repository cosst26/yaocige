package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Dialogue;

import java.util.List;

public interface DialogueMapper extends Mapper<Dialogue> {

    List<Dialogue> list(Dialogue dialogue);

}