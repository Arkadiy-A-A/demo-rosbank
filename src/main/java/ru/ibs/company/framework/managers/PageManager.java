package ru.ibs.company.framework.managers;

import ru.ibs.company.framework.pages.BasePage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class PageManager {

    private Map<String, BasePage> mapPages = new HashMap<>();

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация
     *
     * @return Page
     */
    public <T extends BasePage> T getPage(Class<T> page) {
        if (mapPages.isEmpty() || mapPages.get(page.getName()) == null) {
            try {
                mapPages.put(page.getName(), page.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) mapPages.get(page.getName());
    }

    void clearMapPages() {
        mapPages.clear();
    }
}