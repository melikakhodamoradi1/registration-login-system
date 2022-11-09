package net.guides.springboot.notificationsystem.repository;

import net.guides.springboot.notificationsystem.model.PushToken;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PushTokenRepository extends PagingAndSortingRepository<PushToken, Long> {

    List<PushToken> findAllByUserId(Long userId);
    Optional<PushToken> findByDeviceId(String deviceId);
    Optional<PushToken> findById( Long  id);

    void deleteByDeviceId(String deviceId);



}
