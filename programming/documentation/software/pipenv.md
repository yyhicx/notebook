# pipenv

## Windows

Install: pip3 install pipenv

Set virtualenvs in project: Add `PIPENV_VENV_IN_PROJECT=true` to `environment variables`

Set virtualenvs installation directory: Add WORKON_HOME to `environment variables`

Create virtual environment: pipenv install --python version(such as 3.x)

Remove virtual environment: pipenv --rm

Activate the virtual environment: pipenv shell

Install package: pipenv install package_name

Install dev-package: pipenv install --dev package_name

Remove package: pipenv uninstall package_name
