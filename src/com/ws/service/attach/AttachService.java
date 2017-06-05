package com.ws.service.attach;

import com.bugframework.common.utility.ResultCode;
import com.ws.pojo.attach.Attach;

import java.util.List;

/**
 * Created by ������ on 2017/1/13.
 */
public interface AttachService {
    //����
    ResultCode save(Attach attach);

    /**
     * �޸ĸ���������
     * ID����Ϊ��
     *
     * @param attach �������
     * @return
     */
    ResultCode edit(Attach attach);

    /**
     * �������¸�����ģ��ID
     * @param ids ����ID����
     * @param moduleId ģ��ID
     */
    void edit(String[] ids, String moduleId);

    /**
     * ͨ��ģ��ID����ɾ��״̬
     * @param moduleId ģ��ID
     * @param delFlag ɾ�����߷�ɾ��
     */
    void edit(String moduleId, int delFlag);

    //��ȡ�б�
    List<Attach> list(Attach attach);

    /**
     * ɾ��(ɾ��������Ӳ���ϵ��ļ�����ɾ�����ݿ��¼������)
     *
     * @param id ����ID
     * @return ResultCode
     */
    ResultCode del(String id);

    /**
     * ���·��
     * @param moduleId ģ��ID
     * @return
     */
    List<String> listPath(String moduleId);


}
