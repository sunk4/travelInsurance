package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RiskFactorServiceImplTest {
    @Mock
    private RiskFactorRepository riskFactorRepository;
    @Mock
    private RiskFactorMapper riskFactorMapper;

    @InjectMocks
    private RiskFactorServiceImpl riskFactorService;

    @Test
    void findAllRiskFactors () {
        List<RiskFactorEntity> entities = List.of(new RiskFactorEntity(),
                new RiskFactorEntity());
        List<RiskFactorResponse> responses =
                List.of(new RiskFactorResponse(null, null, 13, null, null),
                        new RiskFactorResponse(null, null, 13,
                                null, null));

        when(riskFactorRepository.findAll()).thenReturn(entities);
        when(riskFactorMapper.entityListToDto(entities)).thenReturn(responses);

        List<RiskFactorResponse> result = riskFactorService.findAllRiskFactors();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(riskFactorRepository, times(1)).findAll();
        verify(riskFactorMapper, times(1)).entityListToDto(entities);
    }

    @Test
    void getRiskFactorById () {
        UUID id = UUID.randomUUID();

        RiskFactorEntity entity = new RiskFactorEntity();
        RiskFactorResponse response = new RiskFactorResponse(id, null, 10,
                null, null);
        when(riskFactorRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(riskFactorMapper.toDto(entity)).thenReturn(response);

        RiskFactorResponse result = riskFactorService.getRiskFactorById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        verify(riskFactorRepository, times(1)).findById(id);
        verify(riskFactorMapper, times(1)).toDto(entity);
    }

    @Test
    void getRiskFactorEntityById () {
        UUID id = UUID.randomUUID();

        RiskFactorEntity entity = new RiskFactorEntity();
        entity.setId(id);
        when(riskFactorRepository.findById(id)).thenReturn(java.util.Optional.of(entity));

        RiskFactorEntity result = riskFactorService.getRiskFactorEntityById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(riskFactorRepository, times(1)).findById(id);
    }
}