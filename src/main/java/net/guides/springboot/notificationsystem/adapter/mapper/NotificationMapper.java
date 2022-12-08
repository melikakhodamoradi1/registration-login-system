package net.guides.springboot.notificationsystem.adapter.mapper;

import net.guides.springboot.notificationsystem.model.NotificationChannel;
import net.guides.springboot.notificationsystem.model.NotificationType;
import net.guides.springboot.notificationsystem.service.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface NotificationMapper {

  NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    default List<NotificationChannel> setChannels(List<NotificationType> types) {
        var channels = new ArrayList<NotificationChannel>();
        for (NotificationType type : types) {
            NotificationChannel notificationChannel = null;

            if (type.equals( NotificationType.PUSH))
                notificationChannel = NotificationChannel.PUSH;
            if (type.equals( NotificationType.INBOX))
                notificationChannel = NotificationChannel.INBOX;

            channels.add(notificationChannel);
        }
        return channels;
    }
}
