import riak

DOC_KEY = 'arbitraty_doc_key'


def print_obj(obj: riak.RiakObject):
    print(f"    Dokument:{obj.data}.")
    print(f"    Klucz:{obj.key}. Ref:{obj}. Exists in DB: {obj.exists}")


def main():
    myClient = riak.RiakClient(pb_port=8098, protocol='http')

    myBucket = myClient.bucket('s23765')

    document = {
            'imie': 'Beniamin',
            'nazwisko': 'Kalinowski',
            'indeks': 's23765',
            'rok_urodzenia': 1986,
            'waga': 82,
    }

    print("Stworzenie dokumentu RiakObject.")
    riak_object = myBucket.new(DOC_KEY, data=document)
    print_obj(riak_object)
    print("Umieszczanie dokumentu w bazie.")
    stored = riak_object.store()
    print_obj(stored)

    print("Pobieranie dokumentu z bazy...")
    fetched = myBucket.get(DOC_KEY)
    print_obj(fetched)
    assert document == fetched.data
    print("Modyfikacja dokumentu...")
    fetched.data['waga'] = 80.5
    print("Umieszczanie dokumentu w bazie.")
    print_obj(fetched)
    fetched.store()

    print("Pobieranie dokumentu z bazy...")
    fetched_2 = myBucket.get(DOC_KEY)
    print_obj(fetched_2)
    assert document != fetched_2.data

    print("Usuwanie dokumentu z bazy...")
    removed = myBucket.delete(DOC_KEY)
    print_obj(removed)

    print("Pobieranie dokumentu z bazy...")
    fetched = myBucket.get(DOC_KEY)
    print_obj(fetched)


if __name__ == '__main__':
    main()

