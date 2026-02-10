package com.chitkara.bfhl.service;

import com.chitkara.bfhl.util.MathUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BfhlService {

    public Object process(Map<String, Object> input, AiService aiService) {

        if (input.containsKey("fibonacci")) {
            return MathUtils.fibonacci((Integer) input.get("fibonacci"));
        }

        if (input.containsKey("prime")) {
            List<Integer> nums = (List<Integer>) input.get("prime");
            return nums.stream().filter(MathUtils::isPrime).toList();
        }

        if (input.containsKey("lcm")) {
            List<Integer> nums = (List<Integer>) input.get("lcm");
            return MathUtils.lcm(nums.stream().mapToInt(Integer::intValue).toArray());
        }

        if (input.containsKey("hcf")) {
            List<Integer> nums = (List<Integer>) input.get("hcf");
            return MathUtils.hcf(nums.stream().mapToInt(Integer::intValue).toArray());
        }

        if (input.containsKey("AI")) {
            return aiService.getAnswer(input.get("AI").toString());
        }

        throw new IllegalArgumentException("Invalid request body");
    }
}
