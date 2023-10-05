package ru.itmo.se.bl.lab4.utils;

import ru.itmo.se.bl.lab4.dto.TouristInfoDTO;
import ru.itmo.se.bl.lab4.entity.TouristInfo;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    public static final class TouristInfoConverter {
        public static TouristInfo dtoToEntity(TouristInfoDTO dto) {
            TouristInfo touristInfo = new TouristInfo();
            touristInfo.setId(null);
            touristInfo.setPassport(
                    dto.getPassport() != null
                            ? dto.getPassport().toEntity()
                            : null);
            touristInfo.setInternationalPassport(
                    dto.getInternationalPassport() != null
                            ? dto.getInternationalPassport().toEntity()
                            : null);
            touristInfo.setEmail(dto.getEmail());

            return touristInfo;
        }

        public static List<TouristInfo> dtosToEntityList(Iterable<TouristInfoDTO> dtos) {
            List<TouristInfo> dtoList = new ArrayList<>();

            dtos.forEach(dto -> dtoList.add(dtoToEntity(dto)));

            return dtoList;
        }
    }
}
