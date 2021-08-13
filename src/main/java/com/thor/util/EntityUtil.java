package com.thor.util;

import com.thor.entity.Inspector;
import com.thor.entity.InspectorRegistry;
import com.thor.entity.Manager;
import com.thor.entity.ManagerRegistry;

public class EntityUtil {

    public static Manager managerRegistryToManager(ManagerRegistry registry) {
        Manager manager = new Manager();
        manager.setName(registry.getName());
        manager.setPhone(registry.getPhone());
        manager.setPassword(registry.getPassword());
        manager.setAge(registry.getAge());
        manager.setGender(registry.getGender());
        manager.setIcon(registry.getIcon());
        manager.setPostId(registry.getPostId());
        manager.setDate(registry.getDate());
        return manager;
    }

    public static Inspector inspectorRegistryToInspector(InspectorRegistry registry) {
        Inspector inspector = new Inspector();
        inspector.setName(registry.getName());
        inspector.setPhone(registry.getPhone());
        inspector.setPassword(registry.getPassword());
        inspector.setAge(registry.getAge());
        inspector.setGender(registry.getGender());
        inspector.setIcon(registry.getIcon());
        inspector.setPostId(registry.getPostId());
        inspector.setManagerId(registry.getManagerId());
        inspector.setDate(registry.getDate());
        return inspector;
    }

}
