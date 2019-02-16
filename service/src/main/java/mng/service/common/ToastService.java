package mng.service.common;

import mng.dao.common.ToastDao;
import mng.model.common.Toast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Service
public class ToastService {
    private ToastDao toastDao;
    @Autowired
    public ToastService(ToastDao toastDao){
        this.toastDao = toastDao;
    }
    public List<Toast> findAll(){
        return toastDao.findAllByValid(Toast.TOAST_VALID_YES);
    }
    public Toast findByCode(String code){
        return toastDao.findByCode(code);
    }
    public void save(Toast toast){
        toastDao.saveAndFlush(toast);
    }
}
