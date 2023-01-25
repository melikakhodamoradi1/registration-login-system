package net.guides.springboot.notificationsystem.adapter.mapper;

import com.vasl.ario.crudutil.entity.Entity;
import javax.annotation.processing.Generated;
import net.guides.springboot.notificationsystem.model.PushToken;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-03T21:53:47+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class PushTokenServiceMapperImpl implements PushTokenServiceMapper {

    @Override
    public void updateEntity(PushToken existingEntity, PushToken newEntity) {
        if ( newEntity == null ) {
            return;
        }

        existingEntity.setId( newEntity.getId() );
        existingEntity.setUserId( newEntity.getUserId() );
        existingEntity.setDeviceId( newEntity.getDeviceId() );
        existingEntity.setToken( newEntity.getToken() );
        existingEntity.setType( newEntity.getType() );
    }

    @Override
    public void updateBaseEntity(Entity existing, Entity existingBaseClone) {
        if ( existingBaseClone == null ) {
            return;
        }

        existing.setId( existingBaseClone.getId() );
        existing.setVersion( existingBaseClone.getVersion() );
        existing.setDeleted( existingBaseClone.getDeleted() );
        existing.setDeletedAt( existingBaseClone.getDeletedAt() );
        existing.setCreatedDate( existingBaseClone.getCreatedDate() );
        existing.setLastModifiedDate( existingBaseClone.getLastModifiedDate() );
    }
}
