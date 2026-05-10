package ru.mephi.vikingdemo.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mephi.vikingdemo.gui.VikingTableModel;
import ru.mephi.vikingdemo.repository.VikingStorage;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 

    private final VikingFactory vikingFactory;
    private final VikingStorage vikingStorage;
    private VikingTableModel tableModel;
    
    @Autowired
    public VikingService(VikingFactory vikingFactory, VikingStorage vikingStorage) {
        this.vikingFactory = vikingFactory;
        this.vikingStorage = vikingStorage;
    }
    
    public void setTableModel(VikingTableModel tableModel) {
        this.tableModel = tableModel;
        refreshTable();
    }

    
    public List<Viking> findAll() {
        return vikingStorage.findAll();
    }
    
    public Viking findById(Long id) {
        return vikingStorage.findById(id);
    }
    
    public Viking save(Viking viking) {
        Viking saved = vikingStorage.save(viking);
        refreshTable();
        return saved;
    }
    
    public Viking update(Viking viking) {
        Viking updated = vikingStorage.update(viking);
        refreshTable();
        return updated;
    }
    
    public List<Viking> generateManyRandom(int count) {
        List<Viking> generated = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            generated.add(vikingFactory.createRandomViking());
        }
        List<Viking> saved = generated.stream()
                .map(vikingStorage::save)
                .toList();
        refreshTable();
        return saved;
    }

    public Viking createRandomViking() {
        Viking viking = vikingFactory.createRandomViking();
        return vikingStorage.save(viking);
    }
    public void deleteById(Long id) {
        vikingStorage.deleteById(id.intValue());
        refreshTable();
    }
    
    private void refreshTable() {
        if (tableModel != null) {
            tableModel.setVikings(vikingStorage.findAll());
        }
    }
}
