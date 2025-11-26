package dev.whitespace.core.repository;

import dev.whitespace.core.entity.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KeyRepository extends JpaRepository<AccountUser, Integer> {
}