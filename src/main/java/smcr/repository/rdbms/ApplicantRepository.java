package smcr.repository.rdbms;

import org.springframework.data.jpa.repository.JpaRepository;

import smcr.domain.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}