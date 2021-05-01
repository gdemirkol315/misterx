using System;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class Station : MonoBehaviour
{
    private const string SPRITE_ASSETS_FOLDER = "Station/";

    public int stationId;
    public HashSet<Connection> connections = new HashSet<Connection>();

    // Start is called before the first frame update
    private void Start()
    {
        
        SpriteRenderer spriteRenderer = gameObject.GetComponent<SpriteRenderer>();

        TMP_Text labelTmpText = gameObject.GetComponentInChildren<TMP_Text>();

        if (isTransportationTypeInConnections(TransportationType.SUBWAY))
        {
            spriteRenderer.sprite = Resources.Load<Sprite>(SPRITE_ASSETS_FOLDER + "station_subway");
            labelTmpText.color = Color.white;
        }
        else if (isTransportationTypeInConnections(TransportationType.BUS))
        {
            spriteRenderer.sprite = Resources.Load<Sprite>(SPRITE_ASSETS_FOLDER + "station_bus");
            labelTmpText.color = Color.black;
        }
        else
        {
            spriteRenderer.sprite = Resources.Load<Sprite>(SPRITE_ASSETS_FOLDER + "station_taxi");
            labelTmpText.color = Color.black;
        }

        labelTmpText.text = stationId.ToString();
    }

    // Update is called once per frame
    private void Update()
    {
    }

    private bool isTransportationTypeInConnections(TransportationType transportationType)
    {
        foreach (Connection connection in connections)
        {
            if (connection.transportationType==transportationType)
            {
                return true;
            }
        }
        return false;
    }
}