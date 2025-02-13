package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.Faq;

public interface FaqService {
    public void insertFaq(Faq dto) throws Exception;
    public void updateFaq(Faq dto) throws Exception;
    public void deleteFaq(long faqNum) throws Exception;
    public Faq findFaqById(long faqNum) throws Exception;
    public List<Faq> listFaq(Map<String, Object> map) throws Exception;
    public int faqDataCount(Map<String, Object> map) throws Exception;
}
