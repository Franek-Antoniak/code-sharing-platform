package com.code.sharing.platform.service;

import com.code.sharing.platform.model.CodeHolder;
import com.code.sharing.platform.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Controller
@Component
public class CRUD {

    @Autowired
    private CodeRepository codeRepository;

    public Optional<CodeHolder> getByUniqueId(String uniqueId) {
        var tempOpt = codeRepository.findByUniqueId(uniqueId);
        if(tempOpt.isPresent()) {
            if(tempOpt.get().setCurrentRestrictionsAndGetState())
                deleteById(tempOpt.get().getId());
            else {
                saveCodeWithoutEdit(tempOpt.get());
                return tempOpt;
            }
        }
        return Optional.empty();
    }

    public void deleteById(long Id) {
        codeRepository.deleteById(Id);
    }

    public void saveAndEditCode(CodeHolder codeHolder){
        codeHolder.checkAndSetRestriction();
        codeHolder.createAndSetUniqueId();
        codeRepository.save(codeHolder);
    }

    public void saveCodeWithoutEdit(CodeHolder codeHolder) {
        codeRepository.save(codeHolder);
    }

    public Optional<List<CodeHolder>> getLatest() {
        return codeRepository.findTop10ByRestrictionOrderByIdDesc(false);
    }
}
