package com.maks.assetaccounting.vaadin.components;

import com.maks.assetaccounting.util.SecurityUtil;
import com.maks.assetaccounting.vaadin.views.MainView;
import com.maks.assetaccounting.vaadin.views.UserView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;

public class AppLayoutClass extends AbstractAppRouterLayout {

    public AppLayoutClass() {

    }

    @Override
    protected void configure(final AppLayout appLayout, final AppLayoutMenu appLayoutMenu) {
        final String mainRoute = UI.getCurrent().getRouter().getUrl(MainView.class);
        final Anchor main = new Anchor(mainRoute, "Asset Accounting");
        main.getStyle().set("color", "black");
        main.getStyle().set("text-decoration", "none");

        appLayout.setBranding(new H3(main));

        setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.HOME.create(),
                "Home", ""));
        if (SecurityUtil.isUserLoggedIn()) {
            setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.BUILDING.create(),
                    "Companies", "companies"));
            setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.RECORDS.create(),
                    "Assets", "assets"));
            if (SecurityUtil.isAccessGranted(UserView.class)) {
                setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.USERS.create(),
                        "Users", "users"));
            }
            setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.SIGN_OUT.create(),
                    "Sign Out", e -> UI.getCurrent().getPage()
                    .executeJavaScript("location.assign('logout')")));
        }
        if (!SecurityUtil.isUserLoggedIn()) {
            setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.SIGN_IN.create(),
                    "Sign In", "sign-in"));
        }
        setMenuItem(appLayoutMenu, new AppLayoutMenuItem(VaadinIcon.ARROW_DOWN.create(),
                "Sign Up", "sign-up"));
    }

    private void setMenuItem(AppLayoutMenu menu, AppLayoutMenuItem menuItem) {
        menuItem.getElement().setAttribute("theme", "icon-on-top");
        menu.addMenuItem(menuItem);
    }
}
