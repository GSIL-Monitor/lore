package mng.dao.common;

import mng.model.common.Toast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface ToastDao extends JpaRepository<Toast, String> {
    /**
     * find all valid toast
     * @param valid valid field
     * @return toast list
     */
    List<Toast> findAllByValid(Boolean valid);

    /**
     * find  toast by code
     * @param code toast code
     * @return toast
     */
    Toast findByCode(String code);
}
