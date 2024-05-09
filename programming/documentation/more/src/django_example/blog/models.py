from django.db import models
from django.utils.translation import ugettext_lazy
from django.contrib.auth import get_user_model

User = get_user_model()


class Article(models.Model):
    STATUS_CHOICES = (
        ("p", ugettext_lazy("Published")),
        ("d", ugettext_lazy("Draft")),
    )

    title = models.CharField(
        verbose_name=ugettext_lazy("Title (*)"), max_length=90, db_index=True
    )
    body = models.TextField(verbose_name=ugettext_lazy("Body"), blank=True)
    author = models.ForeignKey(
        User,
        verbose_name=ugettext_lazy("Author"),
        on_delete=models.CASCADE,
        related_name="articles",
    )
    status = models.CharField(
        ugettext_lazy("Status (*)"),
        max_length=1,
        choices=STATUS_CHOICES,
        default="s",
        null=True,
        blank=True,
    )
    create_date = models.DateTimeField(
        verbose_name=ugettext_lazy("Create Date"), auto_now_add=True
    )

    def __str__(self):
        return self.title

    class Meta:
        ordering = ["-create_date"]
        verbose_name = "Article"
        verbose_name_plural = "Articles"
