package com.example.dragonsden;

import java.util.ArrayList;

public class Validation {
    public ArrayList<ValidationMessage> validate(String text, String rules){
        String[] rules_split = rules.split("[|]");
        //System.out.println(rules_split);
        ArrayList<ValidationMessage> error_list = new ArrayList<>();
        for (int i = 0; i < rules_split.length; i++){
            Rule rule = new Rule(rules_split[i]);
            ValidationMessage vm = checkRules(text, rule);
            if (!vm.passed)
                error_list.add(vm);
        }
        return error_list;
    }

    private class Rule{
        public String name;
        public String option;
        public Rule(String rule){
            if (rule.contains(":")){
                String[] rule_split = rule.split("[:]");
                name = rule_split[0];
                option = rule_split[1];
            }
            else{
                name = rule;
                option = "";
            }
        }
    }

    public class ValidationMessage{
        public boolean passed;
        public String message;
        public ValidationMessage(Boolean _passed){
            passed = _passed;
            message = "";
        }
    }

    private ValidationMessage checkRules(String text, Rule rule){
        ValidationMessage vm = new ValidationMessage(false);

        switch (rule.name){
            case "equals":
                System.out.println(text);
                System.out.println(text == rule.option);
                System.out.println(rule.option);
                if (text == rule.option)
                    vm.passed = true;
                else
                    vm.message = "Did not match";
                break;
            case "required":
                if (text.length() > 0)
                    vm.passed = true;
                else
                    vm.message = "Is required";
                break;
            case "max":
                if (text.length() <= Integer.parseInt(rule.option))
                    vm.passed = true;
                else
                    vm.message = "Max length is "+rule.option+" characters";
                break;
            case "min":
                if (text.length() >= Integer.parseInt(rule.option))
                    vm.passed = true;
                else
                    vm.message = "Min length is "+rule.option+" characters";
                break;
            default:
            break;
        }

        return vm;
    }
}
