package com.chenning.springbootlearn.jpa;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author nchen
 * @Date 2021/7/14 11:22
 * @Version 1.0
 * @Description
 */
public class Demon {

//    private List<UserTasksVo> select_sql() {
//        try {
//            EntityManager em = PersistenceHelper.service.getEntityManager();
//            Query query = em.createNativeQuery("\n" +
//                    "select p.name ,sum(CASE WHEN tab.countjob is NULL THEN 0 ELSE tab.countjob END  ) as   sumjob\n" +
//                    " from  piuser  p    \n" +
//                    " left join  \n" +
//                    "\n" +
//                    "(\n" +
//                    "select user_ref_id,  count(CASE WHEN resourceclassname is NULL THEN 0 ELSE resourceclassname END  ) as countjob from \n" +
//                    "(\n" +
//                    "select a.id,b.user_ref_id,b.classname resourceclassname  from  piresource_assignment a INNER JOIN\n" +
//                    "(\n" +
//                    "    select  piresource.*   from\n" +
//                    "        piresource   LEFT JOIN    piuser on\n" +
//                    "        (piresource.user_ref_class, piresource.user_ref_id)=(piuser.classname, piuser.id)\n" +
//                    "    \n" +
//                    " )b     on  b.id=a.rsrc_ref_id\n" +
//                    "    \n" +
//                    "    where       (\n" +
//                    "               a.plannable_status='NOTOPEN'\n" +
//                    "            or a.plannable_status='INPROCESS'\n" +
//                    "            or a.plannable_status='PENDINGAPPROVAL'\n" +
//                    "            or a.plannable_status='APPROVAL_REJECTED'\n" +
//                    "        )   \n" +
//                    "            and (\n" +
//                    "            a.project_ref_id in (\n" +
//                    "                select\n" +
//                    "                    piproject1_.id \n" +
//                    "                from\n" +
//                    "                    piproject piproject1_ \n" +
//                    "                where\n" +
//                    "                    (\n" +
//                    "                        piproject1_.baseline_type_ref_id<=0 \n" +
//                    "                        or piproject1_.baseline_type_ref_id is null\n" +
//                    "                    ) \n" +
//                    "                    and piproject1_.is_template=0\n" +
//                    "            )\n" +
//                    "        )\n" +
//                    "   \n" +
//                    ")c\n" +
//                    "  GROUP BY   user_ref_id\n" +
//                    "  )tab\n" +
//                    "  \n" +
//                    "  on   tab.user_ref_id=p.id\n" +
//                    " \n" +
//                    "  GROUP BY  id\n"
//            );
            //加入这个才能转为有key value的 直接转对象  不然就是List<Object>[]>
//            query.unwrap(NativeQueryImpl.class)
//                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//            List list = query.getResultList();
//
//            List<UserTasksVo> userTasksVos = JSON.parseArray(JSON.toJSONString(list), UserTasksVo.class);
//
//            return userTasksVos;
//        } catch (Exception e) {
//            return null;
//        }
//    }






//    public <T> List<T> select_hql(Class<T> clazz, PIProject project, PIPlan plan, PIPlanActivity planActivity) {
//        EntityManager em = PersistenceHelper.service.getEntityManager();
//        StringBuffer hql = new StringBuffer("select s from " + clazz.getSimpleName() + " s where 1=1");
//        if (project != null) {
//            hql.append(" and s.projectReference.key.classname ='" + project.getObjectIdentifier().getClassname() + "' ");
//            hql.append(" and s.projectReference.key.id ='" + project.getObjectIdentifier().getId() + "' ");
//        }
//        if (plan != null) {
//            hql.append(" and s.planReference.key.classname ='" + plan.getObjectIdentifier().getClassname() + "' ");
//            hql.append(" and s.planReference.key.id ='" + plan.getObjectIdentifier().getId() + "' ");
//        }
//        if (planActivity != null) {
//            hql.append(" and s.planActivityReference.key.classname ='" + planActivity.getObjectIdentifier().getClassname() + "' ");
//            hql.append(" and s.planActivityReference.key.id ='" + planActivity.getObjectIdentifier().getId() + "' ");
//        }
//        Query query = em.createQuery(hql.toString());
//        List<T> resultList = query.getResultList();
//        return resultList;
//    }








//    private void  update(){
//        EntityManager em = PersistenceHelper.service.getEntityManager();
//        Query query = em.createNativeQuery
//                ("UPDATE piplan_activity \n" +
//                        "SET target_start_date = DATE_ADD( target_start_date, INTERVAL " + timeNunber + " DAY ),\n" +
//                        "target_end_date = DATE_ADD( target_end_date, INTERVAL " + timeNunber + " DAY ) \n" +
//                        "WHERE\n" +
//                        "root_ref_id = " + planid + " \n" +
//                        "AND plannable_status = 'SCHEDULED'");
//        int executeUpdate = query.executeUpdate();
//        System.out.println("变更受影响的活动数为" + executeUpdate);
//    }





    /**
     * 某图号下，listUser所拥有的的在库数量
     *
     * @param drawingNumber
     * @param piUsers
     * @return
     *
     * SELECT
     * count( stoperatio0_.classname ) AS col_0_0_
     * FROM
     * stoperation_log stoperatio0_
     * WHERE
     * stoperatio0_.drawing_number =?
     * AND ( stoperatio0_.operation_type =? OR stoperatio0_.operation_type =? )
     * AND ( stoperatio0_.creator_ref_id IN ( ?, ? ) )
     */
//    @Override
//    public Integer counting(String drawingNumber, List<PIUser> piUsers) {
//        EntityManager em = PersistenceHelper.service.getEntityManager();
//        Long result = null;
//        try {
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery criteriaQuery = cb.createQuery();
//            List<Predicate> predicates = new ArrayList<>();
//            Root root = criteriaQuery.from(STOperationLog.class);
//            Path key1 = root.get("drawingNumber");//图号
//            Path key2 = root.get("creator").get("key").get("id");
//            Path key3 = root.get("operationType");
//            predicates.add(cb.equal(key1, drawingNumber));
//
//            predicates.add(cb.or(cb.equal(key3, OperationType.STORAGE), cb.equal(key3, OperationType.WAITSTOCKOUT)));
//
//
//            List<Long> list = piUsers.stream().map(user -> user.getObjectIdentifier().getId()).collect(Collectors.toList());
//            predicates.add(cb.in(key2).value(list));
//
//            criteriaQuery.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));
//            TypedQuery query = em.createQuery(criteriaQuery);
//            result = (Long) query.getSingleResult();
//            System.out.println("数量为=============" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result.intValue();
//    }

}
