package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.CompanyDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.mappers.CompanyMapper;
import gaiduchek.maksym.api.model.Company;
import gaiduchek.maksym.api.repository.CompanyRepository;
import gaiduchek.maksym.api.security.services.interfaces.AccessService;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private SecurityProvider securityProvider;

    @Mock
    private SellerService sellerService;

    @Mock
    private AccessService accessService;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private Company company;
    private CompanyDto companyDto;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setId(1L);
        company.setName("Test Company");

        companyDto = new CompanyDto();
        companyDto.setName("Test Company DTO");
    }

    @Test
    void findById_ShouldReturnCompany_WhenExists() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        var result = companyService.findById(1L);

        assertThat(result).isPresent().contains(company);
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldReturnEmptyOptional_WhenNotExists() {
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        var result = companyService.findById(1L);

        assertThat(result).isEmpty();
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void getByIdOrThrow_ShouldReturnCompany_WhenExists() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        var result = companyService.getByIdOrThrow(1L);

        assertThat(result).isEqualTo(company);
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void getByIdOrThrow_ShouldThrowException_WhenNotExists() {
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> companyService.getByIdOrThrow(1L))
                .isInstanceOf(EntityNotFoundException.class);

        verify(companyRepository, times(1)).findById(1L);
    }

    /*@Test
    void find_ShouldReturnSearchCompanyDto() {
        CompanyFilter filter = mock(CompanyFilter.class);
        Pageable pageable = mock(Pageable.class);
        Page<Company> companyPage = new PageImpl<>(List.of(company));

        when(filter.buildSpecification()).thenReturn(null);
        when(filter.buildPageable()).thenReturn(pageable);
        when(companyRepository.findAll(null, pageable)).thenReturn(companyPage);
        when(companyMapper.toDtos(List.of(company))).thenReturn(List.of(companyDto));

        SearchCompanyDto result = companyService.find(filter);

        assertThat(result).isNotNull();
        assertThat(result.getCompanies()).hasSize(1);
        assertThat(result.getCompanies().get(0).getName()).isEqualTo("Test Company DTO");

        verify(companyRepository, times(1)).findAll(null, pageable);
    }*/

    @Test
    void create_ShouldSaveNewCompany() {
        when(companyRepository.existsByName("Test Company DTO")).thenReturn(false);
        when(companyMapper.toEntity(companyDto)).thenReturn(company);
        when(securityProvider.fetchUserId()).thenReturn(1L);
        when(sellerService.getByIdOrThrow(1L)).thenReturn(null);
        when(companyRepository.save(company)).thenReturn(company);

        var result = companyService.create(companyDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Company");

        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void create_ShouldThrowException_WhenNameAlreadyExists() {
        when(companyRepository.existsByName("Test Company DTO")).thenReturn(true);

        assertThatThrownBy(() -> companyService.create(companyDto))
                .isInstanceOf(ValidationException.class);

        verify(companyRepository, never()).save(any());
    }

    @Test
    void update_ShouldUpdateCompany() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.existsByNameAndIdNot("Test Company DTO", 1L)).thenReturn(false);
        when(companyRepository.save(company)).thenReturn(company);

        var result = companyService.update(1L, companyDto);

        assertThat(result.getName()).isEqualTo("Test Company DTO");
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void update_ShouldThrowException_WhenNameAlreadyExists() {
        when(companyRepository.existsByNameAndIdNot("Test Company DTO", 1L)).thenReturn(true);

        assertThatThrownBy(() -> companyService.update(1L, companyDto))
                .isInstanceOf(ValidationException.class);

        verify(companyRepository, never()).save(any());
    }

    @Test
    void delete_ShouldMarkCompanyAndProductsAsDeleted() {
        company.setProducts(List.of());

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(company)).thenReturn(company);

        companyService.delete(1L);

        assertThat(company.getDeleted()).isTrue();
        verify(companyRepository, times(1)).save(company);
    }
}
