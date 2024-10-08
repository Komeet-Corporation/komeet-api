package fr.btssio.komeet.komeetapi.domain.mapper;

import fr.btssio.komeet.komeetapi.domain.data.Role;
import fr.btssio.komeet.komeetapi.domain.dto.RoleDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoleMapper {

    public RoleMapper() {
    }

    public RoleDto toDto(@NotNull Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setUuid(UUID.fromString(role.getUuid()));
        roleDto.setLabel(role.getLabel());
        return roleDto;
    }
}
