package fr.btssio.komeet.komeetapi.domain.mapper;

import fr.btssio.komeet.komeetapi.domain.data.Company;
import fr.btssio.komeet.komeetapi.domain.dto.CompanyDto;
import fr.btssio.komeet.komeetapi.domain.dto.RoleDto;
import fr.btssio.komeet.komeetapi.domain.dto.RoomDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {

    private final RoomMapper roomMapper;
    private final RoleMapper roleMapper;

    public CompanyMapper(RoomMapper roomMapper, RoleMapper roleMapper) {
        this.roomMapper = roomMapper;
        this.roleMapper = roleMapper;
    }

    public CompanyDto toDto(@NotNull Company company) {
        RoleDto roleDto = roleMapper.toDto(company.getRole());
        CompanyDto companyDto = new CompanyDto(company.getEmail(), company.getUuid(), roleDto, company.getName(), company.getPhone());
        List<RoomDto> rooms = company.getRooms().stream().map(roomMapper::toDto).toList();
        companyDto.setRooms(rooms);
        return companyDto;
    }
}
