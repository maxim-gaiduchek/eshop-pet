package gaiduchek.maksym.api.controllers.users;

import gaiduchek.maksym.api.dto.search.SearchSellerDto;
import gaiduchek.maksym.api.dto.search.SearchSellerQueryDto;
import gaiduchek.maksym.api.dto.users.SellerDto;
import gaiduchek.maksym.api.mappers.SellerMapper;
import gaiduchek.maksym.api.services.interfaces.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final SellerMapper sellerMapper;

    @GetMapping("/{id}")
    public SellerDto get(@PathVariable Long id) {
        var seller = sellerService.getByIdOrThrow(id);
        return sellerMapper.toDto(seller);
    }

    @GetMapping
    public SearchSellerDto findAll(@ModelAttribute SearchSellerQueryDto queryDto) {
        var filter = sellerMapper.queryToFilter(queryDto);
        return sellerService.find(filter);
    }

    @PostMapping
    @PreAuthorize("@accessService.isWorker()")
    public SellerDto create(@RequestBody @Valid SellerDto sellerDto) {
        var seller = sellerService.create(sellerDto);
        return sellerMapper.toDto(seller);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@accessService.isUserThemself(#id) or @accessService.isWorker()")
    public SellerDto update(@PathVariable Long id,
                            @RequestBody @Valid SellerDto sellerDto) {
        var seller = sellerService.update(id, sellerDto);
        return sellerMapper.toDto(seller);
    }
}
