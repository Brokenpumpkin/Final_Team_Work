package com.hunter.computerhunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/computers")
public class computerController {

    @Autowired
    private computerRepository computerrepository;

    @Autowired
    private computerService service;

    @GetMapping(value = "/index")
    public String index(ModelMap modelMap) {
        modelMap.put("msg", "SpringBoot Ajax 示例");
        return "index";
    }

    /**增加电脑*/
    @PostMapping("/addComputer")
    @ResponseBody
    public computer create(computer compu){
        compu.setBrand(compu.getBrand());
        compu.setBuyUrl1(compu.getBuyUrl1());
        compu.setBuyUrl2(compu.getBuyUrl2());
        compu.setBuyUrl3(compu.getBuyUrl3());
        compu.setCharacterOne(compu.getCharacterOne());
        compu.setCharacterTwo(compu.getCharacterTwo());
        compu.setCharacterThree(compu.getCharacterThree());
        compu.setCpuWeight(compu.getCpuWeight());
        compu.setCpuName(compu.getCpuName());
        compu.setMemoryWeight(compu.getMemoryWeight());
        compu.setMemoryName(compu.getMemoryName());
        compu.setDisplayCardWeight(compu.getDisplayCardWeight());
        compu.setDisplayCardName(compu.getDisplayCardName());
        compu.setHardDiskWeight(compu.getHardDiskWeight());
        compu.setHardDiskName(compu.getHardDiskName());
        compu.setModel(compu.getModel());
        compu.setPrice(compu.getPrice());

        return computerrepository.save(compu);
    }

    /**
     * 通过型号查询电脑
     */
    @GetMapping(value = "/findByModel/{model}")
    @ResponseBody
    public computer computerFindByModel(@PathVariable("model")  String model) {
        return computerrepository.findByModel(model).orElse(null);
    }
    /**
     * 更新电脑
     */
    @PutMapping(value = "/updateByModel/{model}")
    @ResponseBody
    public computer computerUpdateByModel(@PathVariable("model") String model,
                                          @RequestParam("brand") String brand,
                                          @RequestParam("buyUrl1") String buyUrl1,
                                          @RequestParam("buyUrl2") String buyUrl2,
                                          @RequestParam("buyUrl3") String buyUrl3,
                                          @RequestParam("characterOne") String characterOne,
                                          @RequestParam("characterTwo") String characterTwo,
                                          @RequestParam("characterThree") String characterThree,
                                          @RequestParam("cupWeight") int cpuWeight,
                                          @RequestParam("cupName") String cpuName,
                                          @RequestParam("memoryWeight") int memoryWeight,
                                          @RequestParam("memoryName") String memoryName,
                                          @RequestParam("displayCardWeight") int displayCardWeight,
                                          @RequestParam("displayCardName") String displayCardName,
                                          @RequestParam("hardDiskWeight") int hardDiskWeight,
                                          @RequestParam("hardDiskName") String hardDiskName,
                                          @RequestParam("price") int price){
        Optional<computer> optional = computerrepository.findByModel(model);
        if(optional.isPresent())
        {
            computer compu = optional.get();
            compu.setBrand(brand);
            compu.setBuyUrl1(buyUrl1);
            compu.setBuyUrl2(buyUrl2);
            compu.setBuyUrl3(buyUrl3);
            compu.setCharacterOne(characterOne);
            compu.setCharacterTwo(characterTwo);
            compu.setCharacterThree(characterThree);
            compu.setCpuWeight(cpuWeight);
            compu.setCpuName(cpuName);
            compu.setMemoryWeight(memoryWeight);
            compu.setMemoryName(memoryName);
            compu.setDisplayCardWeight(displayCardWeight);
            compu.setDisplayCardName(displayCardName);
            compu.setHardDiskWeight(hardDiskWeight);
            compu.setHardDiskName(hardDiskName);
            compu.setPrice(price);

            return computerrepository.save(compu);
        }
        return  null;
    }

    /**
     * 删除电脑
     * @param model
     */
    @DeleteMapping(value = "/deleteComputer/{model}")
    @ResponseBody
    public void computerDelete (@PathVariable("model") String model) {
        computerrepository.deleteByModel(model);
    }

    /**
     * 关键字查询
     * @param one
     * @param two
     * @param three
     * @return
     */
    @GetMapping("/keywordSearch")
    @ResponseBody
    public List<computer> keywordSearch(@RequestParam("one") String one,
                                        @RequestParam("two") String two,
                                        @RequestParam("three") String three) {
        return service.keywordSearch(one, two, three);
    }
    /**
     * 硬件匹配
     */
    @GetMapping("/handwareSearch")
    @ResponseBody
    public List<computer> handwareSearch(@RequestParam("weight") int weight){

    }
}
