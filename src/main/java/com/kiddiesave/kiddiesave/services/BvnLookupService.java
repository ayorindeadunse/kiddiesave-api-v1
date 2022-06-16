package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import java.io.IOException;

public interface BvnLookupService {
    BvnLookupServiceResponse bvnLookup(String bvn) throws IOException;
}
