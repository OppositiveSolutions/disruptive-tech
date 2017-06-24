package com.careerfocus.service;

import com.careerfocus.entity.Bundle;
import com.careerfocus.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sarath on 04/06/17.
 */
@Service
public class BundleService {

    @Autowired
    BundleRepository bundleRepository;

    public Bundle saveBundle(Bundle bundle) {
        return bundleRepository.save(bundle);
    }

}
