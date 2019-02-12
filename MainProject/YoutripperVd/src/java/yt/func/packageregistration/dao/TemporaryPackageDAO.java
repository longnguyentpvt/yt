/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.dao;

import javaclass.common.ytpackage.YTPackageData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import yt.entity.tbl.TemporaryPackage;

/**
 *
 * @author nickn
 */
public class TemporaryPackageDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public long registerNewRegular(String partnerID, String coverType, String durationType, String servingType, int minPersons,
            String timeSchedule, String advancedBooking, int advancedTime, String dateSchedule, int frequencyServing, long registrationTime) {
        Session session = sessionFactory.getCurrentSession();

        TemporaryPackage tempPackage = new TemporaryPackage();
        tempPackage.setPartnerID(partnerID);
        tempPackage.setCoverType(coverType);
        tempPackage.setDurationType(durationType);
        tempPackage.setServingType(servingType);
        tempPackage.setMinPersons(minPersons);
        tempPackage.setTimeSchedule(timeSchedule);
        tempPackage.setAdvancedBooking(advancedBooking);
        tempPackage.setAdvancedTime(advancedTime);
        tempPackage.setDateSchedule(dateSchedule);
        tempPackage.setFrequencyServing(frequencyServing);
        tempPackage.setRegistrationDateTime(registrationTime);
        tempPackage.setApprovalRequired(true);
        tempPackage.setPackageStatus(YTPackageData.TEMPORARY_PACKAGE_STATUS_CREATING);

        session.persist(tempPackage);
        session.flush();
        return tempPackage.getPackageID();
    }

    public long registerNewOpenTimed(String partnerID, String coverType, String durationType, String servingType, int minPersons,
            int servingTimes, String timeSchedule, String dateSchedule, int frequencyServing, int validationWeeks, String quotaType, long registrationTime) {
        Session session = sessionFactory.getCurrentSession();

        TemporaryPackage tempPackage = new TemporaryPackage();
        tempPackage.setPartnerID(partnerID);
        tempPackage.setCoverType(coverType);
        tempPackage.setDurationType(durationType);
        tempPackage.setServingType(servingType);
        tempPackage.setMinPersons(minPersons);
        tempPackage.setServingTimes(servingTimes);
        tempPackage.setTimeSchedule(timeSchedule);
        tempPackage.setDateSchedule(dateSchedule);
        tempPackage.setFrequencyServing(frequencyServing);
        tempPackage.setValidationWeeks(validationWeeks);
        tempPackage.setQuotaType(quotaType);
        tempPackage.setRegistrationDateTime(registrationTime);
        tempPackage.setApprovalRequired(true);
        tempPackage.setPackageStatus(YTPackageData.TEMPORARY_PACKAGE_STATUS_CREATING);

        session.persist(tempPackage);
        session.flush();
        return tempPackage.getPackageID();
    }

    public TemporaryPackage getCategoryInfoOfPackage(long packageID, String partnerID) {
        //get session
        Session session = sessionFactory.getCurrentSession();

        Criteria cri = session.createCriteria(TemporaryPackage.class);
        cri.add(Restrictions.eq("packageID", packageID));

        ProjectionList projectionList = Projections.projectionList().add(Projections.property("categoryID"))
                .add(Projections.property("subCategoryID"))
                .add(Projections.property("otherSubCategory"))
                .add(Projections.property("suitableCouples"))
                .add(Projections.property("suitableElderly"))
                .add(Projections.property("suitableFamily"))
                .add(Projections.property("suitableIndividual"))
                .add(Projections.property("suitableUniversal"))
                .add(Projections.property("genderSuitability"))
                .add(Projections.property("targetLocationID"))
                .add(Projections.property("colorCode"))
                .add(Projections.property("partnerID"));

        cri.setProjection(projectionList);

        Object[] row = (Object[]) cri.uniqueResult();
        TemporaryPackage tempPackage = null;
        if (row != null) {
            String returnPartnerID = (String) row[11];
            if (returnPartnerID.equals(partnerID)) {
                String categoryID = (String) row[0];
                String subCategoryID = (String) row[1];
                String otherSubCategory = (String) row[2];
                boolean suitableCouples = (boolean) row[3];
                boolean suitableElderly = (boolean) row[4];
                boolean suitableFamily = (boolean) row[5];
                boolean suitableIndividual = (boolean) row[6];
                boolean suitableUniversal = (boolean) row[7];
                String genderSuitability = (String) row[8];
                Integer targetLocationID = (Integer) row[9];
                String colorCode = (String) row[10];

                tempPackage = new TemporaryPackage();
                tempPackage.setCategoryID(categoryID);
                tempPackage.setSubCategoryID(subCategoryID);
                tempPackage.setOtherSubCategory(otherSubCategory);
                tempPackage.setSuitableCouples(suitableCouples);
                tempPackage.setSuitableElderly(suitableElderly);
                tempPackage.setSuitableFamily(suitableFamily);
                tempPackage.setSuitableIndividual(suitableIndividual);
                tempPackage.setSuitableUniversal(suitableUniversal);
                tempPackage.setGenderSuitability(genderSuitability);
                tempPackage.setTargetLocationID(targetLocationID);
                tempPackage.setColorCode(colorCode);
            }
        }
        return tempPackage;
    }

    public TemporaryPackage getDescriptionInfoOfPackage(long packageID, String partnerID) {
        //get session
        Session session = sessionFactory.getCurrentSession();

        Criteria cri = session.createCriteria(TemporaryPackage.class);
        cri.add(Restrictions.eq("packageID", packageID));
        cri.setProjection(Projections.projectionList().add(Projections.property("partnerID")).add(Projections.property("servingLanguage"))
                .add(Projections.property("packageStatus")));

        Object[] row = (Object[]) cri.uniqueResult();
        TemporaryPackage tempPackage = null;
        if (row != null) {
            String returnPartnerID = (String) row[0];
            if (returnPartnerID.equals(partnerID)) {
                String servingLanguage = (String) row[1];
                String packageStatus = (String) row[2];
                tempPackage = new TemporaryPackage();
                tempPackage.setServingLanguage(servingLanguage);
                tempPackage.setPackageStatus(packageStatus);
            }
        }
        return tempPackage;
    }

    public TemporaryPackage getPhotoInfoOfPackage(long packageID, String partnerID) {
        //get session
        Session session = sessionFactory.getCurrentSession();

        Criteria cri = session.createCriteria(TemporaryPackage.class);
        cri.add(Restrictions.eq("packageID", packageID));
        cri.setProjection(Projections.projectionList().add(Projections.property("partnerID")).add(Projections.property("coverType"))
                .add(Projections.property("firstCover")).add(Projections.property("secondCover")).add(Projections.property("thirdCover"))
                .add(Projections.property("videoCover")).add(Projections.property("portraitPhoto"))
                .add(Projections.property("firstThumbnail")).add(Projections.property("secondThumbnail")).add(Projections.property("thirdThumbnail"))
                .add(Projections.property("fourthThumbnail")).add(Projections.property("fifthThumbnail")));

        Object[] row = (Object[]) cri.uniqueResult();
        TemporaryPackage tempPackage = null;
        if (row != null) {
            String returnPartnerID = (String) row[0];
            if (returnPartnerID.equals(partnerID)) {
                String coverType = (String) row[1];
                String firstCover = (String) row[2];
                String secondCover = (String) row[3];
                String thirdCover = (String) row[4];
                String videoCover = (String) row[5];
                String portraitPhoto = (String) row[6];
                String firstThumbnail = (String) row[7];
                String secondThumbnail = (String) row[8];
                String thirdThumbnail = (String) row[9];
                String fourthThumbnail = (String) row[10];
                String fifthThumbnail = (String) row[11];
                tempPackage = new TemporaryPackage();
                tempPackage.setCoverType(coverType);
                tempPackage.setFirstCover(firstCover);
                tempPackage.setSecondCover(secondCover);
                tempPackage.setThirdCover(thirdCover);
                tempPackage.setVideoCover(videoCover);
                tempPackage.setPortraitPhoto(portraitPhoto);
                tempPackage.setFirstThumbnail(firstThumbnail);
                tempPackage.setSecondThumbnail(secondThumbnail);
                tempPackage.setThirdThumbnail(thirdThumbnail);
                tempPackage.setFourthThumbnail(fourthThumbnail);
                tempPackage.setFifthThumbnail(fifthThumbnail);
            }
        }
        return tempPackage;
    }

    public boolean updateCategoryAndSubCategory(String partnerID, long packageID, String categoryID, String subCategoryID, String otherSubCategory) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackage "
                + "SET categoryID = :categoryID, subCategoryID = :subCategoryID, otherSubCategory = :otherSubCategory "
                + "WHERE partnerID = :partnerID AND packageID = :packageID AND packageStatus != :packageStatus";

        Query query = session.createQuery(hql);
        query.setParameter("categoryID", categoryID).setParameter("subCategoryID", subCategoryID)
                .setParameter("otherSubCategory", otherSubCategory).setParameter("partnerID", partnerID)
                .setParameter("packageID", packageID).setParameter("packageStatus", YTPackageData.TEMPORARY_PACKAGE_STATUS_DELETED);

        int row = query.executeUpdate();
        return row > 0;
    }

    public boolean updateSuitability(String partnerID, long packageID, boolean suitableCouples, boolean suitableElderly, boolean suitableFamily,
            boolean suitableIndividual, boolean suitableUniversal, String genderSuitability) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackage "
                + "SET suitableCouples = :suitableCouples, suitableElderly = :suitableElderly, suitableFamily = :suitableFamily, "
                + "suitableIndividual = :suitableIndividual, suitableUniversal = :suitableUniversal, genderSuitability = :genderSuitability "
                + "WHERE partnerID = :partnerID AND packageID = :packageID AND packageStatus != :packageStatus";

        Query query = session.createQuery(hql);
        query.setParameter("suitableElderly", suitableElderly).setParameter("suitableCouples", suitableCouples).setParameter("genderSuitability", genderSuitability)
                .setParameter("suitableFamily", suitableFamily).setParameter("suitableIndividual", suitableIndividual).setParameter("suitableUniversal", suitableUniversal)
                .setParameter("partnerID", partnerID).setParameter("packageID", packageID).setParameter("packageStatus", YTPackageData.TEMPORARY_PACKAGE_STATUS_DELETED);

        int row = query.executeUpdate();
        return row > 0;
    }

    public boolean updateTargetLocaiton(String partnerID, long packageID, Integer targetLocationID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackage "
                + "SET targetLocationID = :targetLocationID "
                + "WHERE partnerID = :partnerID AND packageID = :packageID AND packageStatus != :packageStatus";

        Query query = session.createQuery(hql);
        query.setParameter("targetLocationID", targetLocationID).setParameter("partnerID", partnerID)
                .setParameter("packageID", packageID).setParameter("packageStatus", YTPackageData.TEMPORARY_PACKAGE_STATUS_DELETED);

        int row = query.executeUpdate();
        return row > 0;
    }

    public boolean updatePackageColor(String partnerID, long packageID, String colorCode) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackage "
                + "SET colorCode = :colorCode "
                + "WHERE partnerID = :partnerID AND packageID = :packageID AND packageStatus != :packageStatus";

        Query query = session.createQuery(hql);
        query.setParameter("colorCode", colorCode).setParameter("partnerID", partnerID)
                .setParameter("packageID", packageID).setParameter("packageStatus", YTPackageData.TEMPORARY_PACKAGE_STATUS_DELETED);

        int row = query.executeUpdate();
        return row > 0;
    }

    public boolean updateServingLanguage(long packageID, String servingLanguage) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackage "
                + "SET servingLanguage = :servingLanguage "
                + "WHERE packageID = :packageID";

        Query query = session.createQuery(hql);
        query.setParameter("servingLanguage", servingLanguage).setParameter("packageID", packageID);

        int row = query.executeUpdate();
        return row > 0;
    }

    public String getStatusOfTempPackage(long packageID, String partnerID) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(TemporaryPackage.class);
        cri.add(Restrictions.eq("packageID", packageID));
        cri.setProjection(Projections.projectionList().add(Projections.property("partnerID"))
                .add(Projections.property("packageStatus")));
        Object[] row = (Object[]) cri.uniqueResult();
        String returnPartnerID = (String) row[0];
        if (returnPartnerID.equalsIgnoreCase(partnerID)) {
            return (String) row[1];
        }
        return null;
    }

    public boolean activeDescriptionEditing(long packageID) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE TemporaryPackage "
                + "SET packageStatus = :requireApproveEditingStatus "
                + "WHERE packageID = :packageID";
        Query query = session.createQuery(hql);
        query.setParameter("requireApproveEditingStatus", YTPackageData.TEMPORARY_PACKAGE_STATUS_REQUIRED_APPROVAL_EDITING).setParameter("packageID", packageID);

        int row = query.executeUpdate();
        return row > 0;
    }

    public String updateFirstCover(long packageID, String newCover) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE TemporaryPackage "
                + "SET FirstCover = :newCover "
                + "OUTPUT DELETED.FirstCover "
                + "WHERE PackageID = :packageID";
        SQLQuery query = session.createSQLQuery(sql).addScalar("FirstCover", StringType.INSTANCE);
        query.setParameter("packageID", packageID);
        query.setParameter("newCover", newCover);

        String oldCover = (String) query.uniqueResult();
        return oldCover;
    }

    public String updateSecondCover(long packageID, String newCover) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE TemporaryPackage "
                + "SET SecondCover = :newCover "
                + "OUTPUT DELETED.SecondCover "
                + "WHERE PackageID = :packageID";
        SQLQuery query = session.createSQLQuery(sql).addScalar("SecondCover", StringType.INSTANCE);
        query.setParameter("packageID", packageID);
        query.setParameter("newCover", newCover);

        String oldCover = (String) query.uniqueResult();
        return oldCover;
    }

    public String updateThirdCover(long packageID, String newCover) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "UPDATE TemporaryPackage "
                + "SET ThirdCover = :newCover "
                + "OUTPUT DELETED.ThirdCover "
                + "WHERE PackageID = :packageID";
        SQLQuery query = session.createSQLQuery(sql).addScalar("ThirdCover", StringType.INSTANCE);
        query.setParameter("packageID", packageID);
        query.setParameter("newCover", newCover);

        String oldCover = (String) query.uniqueResult();
        return oldCover;
    }
}
