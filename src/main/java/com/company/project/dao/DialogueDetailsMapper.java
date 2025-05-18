package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.DialogueDetails;

import java.util.List;

public interface DialogueDetailsMapper extends Mapper<DialogueDetails> {

    List<DialogueDetails> list(DialogueDetails dialogueDetails);

}