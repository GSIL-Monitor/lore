package mng.service.lore;

import mng.dao.lore.KnowDao;
import mng.model.lore.Lore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Service
public class KnowService {
    private KnowDao knowDao;
    @Autowired
    public KnowService(KnowDao knowDao){
        this.knowDao = knowDao;
    }

    public List<Lore> findAll(){
        return knowDao.findAll();
    }

    public Lore findByCode(String code){
        return knowDao.findByCode(code);
    }

    public List<Lore> findListByParentCode(String paretnCode){
        return knowDao.findAllByParentCode(paretnCode);
    }
}
