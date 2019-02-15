package mng.dao.lore;

import mng.model.lore.Lore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface KnowDao  extends JpaRepository<Lore, String> {
    /**
     * find lore by code
     * @param code code
     * @return lore
     */
    Lore findByCode(String code);
    /**
     * find lore list by parentCode
     * @param parentCode parentCode
     * @return lore list
     */
    List<Lore> findAllByParentCode(String parentCode);
}
