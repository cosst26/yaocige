package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.DialogueDetails;

public interface DialogueDetailsService extends Service<DialogueDetails> {

    Result list(DialogueDetails dialogueDetails);
}
