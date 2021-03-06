package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.ActivityPhoto;

public interface ActivityPhotoDAO {
	List<ActivityPhoto> getActivityPhotoByActivityId(int id);

	List<ActivityPhoto> getActivityPhotoByActivityIdAndBaseAndRange(int id,
			int base, int range);

	int getActivityPhotosCountByActivityId(int activityId);

	List<ActivityPhoto> getRecentActivityPhotoByActivityId(int id);

	ActivityPhoto getActivityPhotoById(int id);

	void insertActivityPhoto(ActivityPhoto activityPhoto);

	void deleteActivityPhoto(int id);

	void updateActivityPhoto(ActivityPhoto activityPhoto);
}
