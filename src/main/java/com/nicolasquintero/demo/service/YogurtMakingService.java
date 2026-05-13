package com.nicolasquintero.demo.service;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.nicolasquintero.demo.domain.repository.RecipeRepository;
import com.nicolasquintero.demo.domain.repository.TemperatureLogRepository;
import com.nicolasquintero.demo.domain.repository.YogurtBatchRepository;
import com.nicolasquintero.demo.exception.BusinessException;
import com.nicolasquintero.demo.model.Recipe;
import com.nicolasquintero.demo.model.YogurtBatch;

@Service
public class YogurtMakingService {

    private final YogurtBatchRepository batchRepository = null;
    private final RecipeRepository recipeRepository = null;
    private final TemperatureLogRepository temperatureLogRepository = null;
    private final TemperatureControlService temperatureControlService = new TemperatureControlService();
    public @Nullable Object getBatchesByStatus;
    public @Nullable Object getAllBatches;

    public YogurtBatch startNewBatch(Long recipeId, Double customMilkVolume, Double customStartAmount){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new BusinessException("Recipe")); //aca especifico el error o excepcion
        return null;
    }

    public YogurtBatch startHeating(Long batchId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'startHeating'");
    }

    public YogurtBatch startInoculating(Long batchId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'startInoculating'");
    }

    public YogurtBatch startIncubation(Long batchId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'startIncubation'");
    }

    public YogurtBatch startRefrigeration(Long batchId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'startRefrigeration'");
    }

    public YogurtBatch completeBatch(Long batchId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'completeBatch'");
    }

    public YogurtBatch markAsFailed(Long batchId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'markAsFailed'");
    }

}
