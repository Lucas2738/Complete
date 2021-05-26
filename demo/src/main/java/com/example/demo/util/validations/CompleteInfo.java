package com.example.demo.util.validations;

import javax.validation.GroupSequence;

@GroupSequence({BasicInfo.class, AdvanceInfo.class})
public interface CompleteInfo {
}
