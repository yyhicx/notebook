"""
ASGI config for example project.

It exposes the ASGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/3.2/howto/deployment/asgi/
"""

import os

from django.core.asgi import get_asgi_application
from django.urls import path, re_path
from channels.auth import AuthMiddlewareStack
from channels.routing import ProtocolTypeRouter, URLRouter
import html_example.routing
import django_eventstream

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'django_example.settings')

application = ProtocolTypeRouter({
    'http': URLRouter([
        path('html_example/events/', AuthMiddlewareStack(
            URLRouter(django_eventstream.routing.urlpatterns)
        ), { 'channels': ['time'] }),
        re_path(r'', get_asgi_application()),
    ]),
    'websocket': AuthMiddlewareStack(
        URLRouter(
            html_example.routing.websocket_urlpatterns
        )
    ),
})
