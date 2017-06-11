package com.lantushenko.experimental.stub.repositories;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base interface for all repositories. Propagation is set to Mandatory
 * because all repository transactions should be defined on services layer
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface AbstractRepository {

}
